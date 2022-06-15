package com.zcl.controller;

import com.alibaba.fastjson.JSON;
import com.zcl.entity.User;
import com.zcl.service.UserService;
import com.zcl.utils.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 项目名称：ssm_hotel
 * 描述：用户控制器
 *
 * @author zhong
 * @date 2022-05-21 14:52
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 注入业务层
     */
    @Resource
    UserService userService;

    /**
     * 根据手机号查询用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        User login = userService.login(loginName, password);
        if(login != null){
            map.put(SystemConstant.EXIST,true);
            // 清空密码
            login.setPassword(null);
            // 保存登录数据到session会话中
            session.setAttribute(SystemConstant.FRONT_LOGINUSER,login);
        }else{
            map.put(SystemConstant.EXIST,false);
            map.put(SystemConstant.MESSAGES,"登录失败，请检查账号和密码");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据手机号查询用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkPhone")
    public String checkPhone(String phone){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(userService.findUserByPhone(phone) != null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"该手机号已被注册，请重新输入");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据登录名称查询用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public String checkName(String loginName){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(userService.findUserByName(loginName) != null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"该用户名已经存在，请重新输入");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 注册用户数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public String addUsers(User user){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(userService.AddUsers(user) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"账号注册成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"账号注册失败");
        }
        return JSON.toJSONString(map);
    }
}
