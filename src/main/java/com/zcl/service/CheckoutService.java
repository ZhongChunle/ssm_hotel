package com.zcl.service;

import com.zcl.entity.Checkout;

/**
 * 项目名称：ssm_hotel
 * 描述：退房处理业务层
 *
 * @author zhong
 * @date 2022-05-23 16:10
 */
public interface CheckoutService {
    /**
     * 退房信息添加
     * @param checkout
     * @return
     */
    int addCheckout(Checkout checkout);
}
