package com.zcl.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 项目名称：ssm_hotel
 * 描述：负责跳转后台管理页面
 *
 * @author zhong
 * @date 2022-05-15 11:07
 */
@Controller
@RequestMapping("/admin")
public class SystemController {
    /**
     * 员工登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String login(){
        return "admin/login";
    }

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/home.html")
    public String home() {
        return "admin/home";
    }

    /**
     * 404首页
     * @return
     */
    @RequestMapping("/404.html")
    public String fff() {
        return "admin/404";
    }

    /**
     * 员工推出登录，清除session
     * @param session
     * @return
     */
    @RequestMapping("/logout.html")
    public String logout(HttpSession session) {
        // 清除session方式一
        // session.removeAttribute(SystemConstant.LOGINUSER);
        // 清除方式二
        session.invalidate();
        // 重定向登录页面
        return "redirect:/admin/login.html";
    }

    /**
     * 去到部门管理页面
     * @return
     */
    @RequestMapping("/toDeptManager")
    public String toDeptManager(){
        return "admin/dept/deptManager";
    }

    /**
     * 去到角色管理页面
     * @return
     */
    @RequestMapping("/toRoleManager")
    public String toRoleManager(){
        return "admin/role/roleManager";
    }

    /**
     * 去到员工管理页面
     * @return
     */
    @RequestMapping("/toEmployeeManager")
    public String toEmployeeManager(){
        return "admin/employee/employeeManager";
    }

    /**
     * 去到员菜单理页面
     * @return
     */
    @RequestMapping("/toMenuManager")
    public String toMenuManager(){
        return "admin/menu/menuManager";
    }

    /**
     * 去到楼层管理页面
     * @return
     */
    @RequestMapping("/tofloorManager")
    public String toflooeManager(){
        return "admin/floor/floorManager";
    }

    /**
     * 去到房型管理页面
     * @return
     */
    @RequestMapping("/toroomTypeManager")
    public String toroomTypeManager(){
        return "admin/roomtype/roomTypeManager";
    }

    /**
     * 去到房间管理页面
     * @return
     */
    @RequestMapping("/toroomManager")
    public String toroomManager(){
        return "admin/room/roomManager";
    }

    /**
     * 去到预定管理页面
     * @return
     */
    @RequestMapping("/toOrdersManager")
    public String toOrdersManager(){
        return "admin/orders/ordersManager";
    }

    /**
     * 去到入住管理页面
     * @return
     */
    @RequestMapping("/toCheckinManager")
    public String toCheckinManager(){
        return "admin/checkin/checkinManager";
    }
}
