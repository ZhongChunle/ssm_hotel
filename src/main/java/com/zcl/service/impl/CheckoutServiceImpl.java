package com.zcl.service.impl;

import com.zcl.dao.CheckinMapper;
import com.zcl.dao.CheckoutMapper;
import com.zcl.dao.OrdersMapper;
import com.zcl.dao.RoomTypeMapper;
import com.zcl.entity.Checkin;
import com.zcl.entity.Checkout;
import com.zcl.entity.Orders;
import com.zcl.entity.RoomType;
import com.zcl.service.CheckoutService;
import com.zcl.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-23 16:11
 */
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    /**
     * 注入持久层
     */
    @Resource
    CheckoutMapper checkoutMapper;

    /**
     * 注入入住管理
     */
    @Resource
    CheckinMapper checkinMapper;

    /**
     * 注入预订管理
     */
    @Resource
    OrdersMapper ordersMapper;

    /**
     * 注入房间类型管理
     */
    @Resource
    RoomTypeMapper roomTypeMapper;

    /**
     * 添加退房数据
     * @param checkout
     * @return
     */
    public int addCheckout(Checkout checkout) {
        // 1、添加退房记录
        checkout.setCreateDate(new Date());
        checkout.setCheckOutNumber(UUIDUtils.randomUUID());
        // 调用添加退房信息的方法
        int count = checkoutMapper.addCheckout(checkout);
        if(count > 0){
            // 2、修改入住管理t_checkin表中的状态为2【已退房】
            Checkin checkin = checkinMapper.finfById(checkout.getCheckInId());
            checkin.setStatus(2);
            // 调用修改入住订单方法
            checkinMapper.updateCheckin(checkin);

            // 3、修改t_orders预订表中的状态为【4】已完成
            Orders orders = new Orders();
            orders.setId(checkin.getOrdersid());
            orders.setStatus(4);
            // 调用修改的方法
            ordersMapper.updateOrders(orders);

            // 4、修改t_room_type房型表的数据
            RoomType roomType = roomTypeMapper.findById(checkin.getRoomtypeid());
            // 房间可用数量
            roomType.setAvilablenum(roomType.getAvilablenum()+1);
            // 房间已住数量
            roomType.setLivednum(roomType.getLivednum()-1);
            roomTypeMapper.updateRoomType(roomType);
        }
        return count;
    }
}
