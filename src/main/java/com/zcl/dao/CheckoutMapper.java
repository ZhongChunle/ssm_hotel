package com.zcl.dao;

import com.zcl.entity.Checkout;

/**
 * 项目名称：ssm_hotel
 * 描述：退房办理持久层
 *
 * @author zhong
 * @date 2022-05-23 16:04
 */
public interface CheckoutMapper {

    /**
     * 退房信息添加
     * @param checkout
     * @return
     */
    int addCheckout(Checkout checkout);
}
