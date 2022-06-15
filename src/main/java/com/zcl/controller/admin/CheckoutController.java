package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.zcl.dao.CheckoutMapper;
import com.zcl.entity.Checkin;
import com.zcl.entity.Checkout;
import com.zcl.entity.Employee;
import com.zcl.service.CheckoutService;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：办理退房控制器
 *
 * @author zhong
 * @date 2022-05-23 17:55
 */
@RestController
@RequestMapping("/admin/checkout")
public class CheckoutController {
    @Resource
    CheckoutService checkoutService;

    @RequestMapping("/addCheckout")
    public String addCheckout(Checkout checkout, HttpSession session){
        System.out.println("退房接收数据："+checkout);
        Map<String,Object> map = new HashMap<String,Object>();
        //获取当前登录用户
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //退房人【当前登录用户】
        checkout.setCreatedBy(employee.getId());
        //调用添加退房信息的方法
        if(checkoutService.addCheckout(checkout)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"办理退房成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"办理退房失败");
        }
        return JSON.toJSONString(map);
    }
}
