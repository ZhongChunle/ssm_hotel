package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Checkin;
import com.zcl.entity.Employee;
import com.zcl.entity.vo.checkinVo;
import com.zcl.service.CheckinService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：入住管理控制器
 *
 * @author zhong
 * @date 2022-05-23 7:11
 */
@RestController
@RequestMapping("/admin/checkin")
public class CheckinAdminController {
    /**
     * 注入mapper
     */
    @Resource
    CheckinService checkinService;

    /**
     * 登记入住
     *
     * @return
     */
    @RequestMapping("/addCheckin")
    public String addCheckin(Checkin checkin,HttpSession session){
        System.out.println("接收的数据："+checkin);
        Map<String,Object> map = new HashMap<String,Object>();
        //获取当前登录用户
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        //入住创建人【当前登录用户】
        checkin.setCreatedby(employee.getId());
        //调用添加入住信息的方法
        if(checkinService.addCheckin(checkin)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"办理入住成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"办理入住失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 入住管理分页查询控制器
     * @param vo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(checkinVo vo) {
        // 设置分页查询
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        // 调用方法查询数据
        List<Checkin> checkinList = checkinService.findCheckinList(vo);
        System.out.println("查询入住的信息："+checkinList);
        // 封装返回的数据
        PageInfo<Checkin> pageInfo = new PageInfo<Checkin>(checkinList);
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
}
