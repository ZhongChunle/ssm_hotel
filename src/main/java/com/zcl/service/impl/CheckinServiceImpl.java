package com.zcl.service.impl;

import com.zcl.dao.CheckinMapper;
import com.zcl.dao.OrdersMapper;
import com.zcl.dao.RoomMapper;
import com.zcl.dao.RoomTypeMapper;
import com.zcl.entity.Checkin;
import com.zcl.entity.Orders;
import com.zcl.entity.Room;
import com.zcl.entity.RoomType;
import com.zcl.entity.vo.checkinVo;
import com.zcl.service.CheckinService;
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
 * @date 2022-05-23 7:09
 */
@Service
@Transactional
public class CheckinServiceImpl implements CheckinService {
    /**
     * 注入入住持久层
     */
    @Resource
    CheckinMapper checkinMapper;

    /**
     * 注入订单持久层
     */
    @Resource
    OrdersMapper ordersMapper;

    /**
     * 注入房型持久层
     */
    @Resource
    RoomTypeMapper roomTypeMapper;

    @Resource
    RoomMapper roomMapper;

    /**
     * 添加入住信息
     * @param checkin
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int addCheckin(Checkin checkin) {
        // 添加入住信息
        //1-已入住
        checkin.setStatus(1);
        //创建时间
        checkin.setCreatedate(new Date());
        // 调用添加入住信息
        int count = checkinMapper.addCheckin(checkin);
        if(count > 0){
            // 修改预订订单的数据，状态值改为3代表已入住
            Orders orders = new Orders();
            orders.setId(checkin.getOrdersid());
            orders.setStatus(3);
            // 调用修改方法
            ordersMapper.updateOrders(orders);

            // 修改房间状态
            Room room = new Room();
            room.setId(checkin.getRoomid());
            room.setStatus(2);
            roomMapper.updateRoom(room);

            // 修改房型表中的数据
            RoomType roomType = roomTypeMapper.findById(checkin.getRoomtypeid());
            roomType.setLivednum(roomType.getLivednum()+1);
            roomTypeMapper.updateRoomType(roomType);
        }
        return count;
    }

    /**
     * 分页查询入住管理信息
     * @param checkinVo
     * @return
     */
    public List<Checkin> findCheckinList(checkinVo checkinVo) {
        return checkinMapper.findCheckinList(checkinVo);
    }
}
