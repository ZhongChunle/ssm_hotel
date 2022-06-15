package com.zcl.service.impl;

import com.zcl.dao.OrdersMapper;
import com.zcl.dao.RoomMapper;
import com.zcl.dao.RoomTypeMapper;
import com.zcl.entity.Orders;
import com.zcl.entity.Room;
import com.zcl.entity.RoomType;
import com.zcl.entity.vo.OrdersVo;
import com.zcl.service.OrdersService;
import com.zcl.service.RoomService;
import com.zcl.service.RoomTypeService;
import com.zcl.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-22 12:24
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    /**
     * 注入持久层
     */
    @Resource
    OrdersMapper ordersMapper;

    /**
     * 注入房间业务层
     */
    @Resource
    RoomMapper roomMapper;

    /**
     * 注入房间类型业务层
     */
    @Resource
    RoomTypeMapper roomTypeMapper;

    /**
     * 确认订单信息
     * @param orders
     * @return
     */
    public int updateOrders(Orders orders) {
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 分页查询预订订单列表
     * @param vo
     * @return
     */
    public List<Orders> findOrdersList(OrdersVo vo) {
        System.out.println("业务员层在执行查询");
        return ordersMapper.findOrdersList(vo);
    }

    /**
     * 添加预订信息,添加了运行时异常就回滚
     * @param orders
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int addOrders(Orders orders) {
        // 1代表确认
        orders.setStatus(1);
        // 2、订单编号
        orders.setOrderSno(UUIDUtils.randomUUID());
        // 3、预订时间
        orders.setReserveDate(new Date());
        int count = ordersMapper.addOrders(orders);
        // 判断是否修改成功
        if(count > 0){
            // 修改房间状态（1已预订）
            Room room = roomMapper.findById(orders.getRoomId());
            // 修改房间的状态
            room.setStatus(1);
            // 调用修改房间的方法
            roomMapper.updateRoom(room);
            // 修改房间类型信息【已预订+1，可使用-1】
            RoomType roomType = roomTypeMapper.findById(orders.getRoomId());
            // 修改可用房间数据量
            roomType.setAvilablenum(roomType.getAvilablenum()-1);
            // 已预订数量
            roomType.setReservednum(roomType.getReservednum()+1);
            // 调用修改方法
            roomTypeMapper.updateRoomType(roomType);
        }
        return count;
    }
}
