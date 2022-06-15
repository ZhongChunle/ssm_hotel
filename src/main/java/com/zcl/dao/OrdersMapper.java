package com.zcl.dao;

import com.zcl.entity.Orders;
import com.zcl.entity.vo.OrdersVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：预订表持久层
 *
 * @author zhong
 * @date 2022-05-22 12:19
 */
public interface OrdersMapper {
    /**
     * 确认订单
     * @param orders
     * @return
     */
    int updateOrders(Orders orders);

    /**
     * 分页查询预订订单列表
     * @param vo
     * @return
     */
    List<Orders> findOrdersList(OrdersVo vo);

    /**
     * 添加预订信息
     * @param orders
     * @return
     */
    int addOrders(Orders orders);
}
