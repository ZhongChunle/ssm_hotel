package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Menu;
import com.zcl.entity.Orders;
import com.zcl.entity.vo.MenuVo;
import com.zcl.entity.vo.OrdersVo;
import com.zcl.service.OrdersService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：预订管理控制器
 *
 * @author zhong
 * @date 2022-05-22 19:26
 */
@RestController
@RequestMapping("/admin/orders")
public class OrdersAdminController {
    @Resource
    OrdersService ordersService;

    /**
     * 批量确认入住
     * @param ids
     * @return
     */
    @RequestMapping("/barchConfim")
    public String barchConfim(String ids){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        // 将字符串拆分成数组
        String[] idsStr = ids.split(",");
        // 循环确认
        for (int i = 0; i < idsStr.length; i++) {
            // 创建对象【修改状态和查询条件】
            Orders orders = new Orders();
            orders.setId(Integer.valueOf(idsStr[i]));
            orders.setStatus(2);
            // 调用修改方法
            count = ordersService.updateOrders(orders);
            // 判断受影响的行数
            if(count > 0){
                map.put(SystemConstant.SUCCESS,true);
                map.put(SystemConstant.MESSAGES,"订单确认成功");
            }
        }
        if(count <= 0){
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"订单确认失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 确认入住
     * @param orders
     * @return
     */
    @RequestMapping("/confirmOrders")
    public String confirmOrders(Orders orders){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 修改订单的状态
        orders.setStatus(2);
        // 调用修改的方法
        if(ordersService.updateOrders(orders) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"订单确认成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"订单确认失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询订单列表
     * @param ordersVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(OrdersVo ordersVo){
        //设置分页信息
        PageHelper.startPage(ordersVo.getPage(),ordersVo.getLimit());
        //调用分页查询订单列表的方法
        List<Orders> ordersList = ordersService.findOrdersList(ordersVo);
        for (Orders orders : ordersList) {
            System.out.println("查询的预订数据："+orders);
        }
        //创建分页对象
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
