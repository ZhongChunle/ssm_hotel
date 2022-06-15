package com.zcl.controller;

import com.alibaba.fastjson.JSON;
import com.zcl.entity.Orders;
import com.zcl.service.OrdersService;
import com.zcl.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 项目名称：ssm_hotel
 * 描述：预订控制器
 *
 * @author zhong
 * @date 2022-05-22 12:26
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    /**
     * 注入业务层
     */
    @Resource
    OrdersService ordersService;

    /**
     * 添加预订信息
     * @param orders
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrders")
    public String addOrders(Orders orders){
        System.out.println("获取页面添加的数据："+orders);
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用添加方法
        if(ordersService.addOrders(orders) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"酒店预订成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"酒店预订失败，请重试");
        }
        return JSON.toJSONString(map);
    }
}
