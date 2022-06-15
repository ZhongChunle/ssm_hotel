package com.zcl.controller.admin;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Employee;
import com.zcl.entity.vo.EmployeeVo;
import com.zcl.service.EmployeeService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：员工登录控制器
 *
 * @author zhong
 * @date 2022-05-15 14:32
 */
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    @Resource
    EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param username 账号
     * @param password 密码
     * @param session  临时会话
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        // 创建HasMap存储数据
        HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
        // 查询数据
        Employee employe = employeeService.login(username, password);
        // 判断数据
        if (employe != null) {
            // 保存当前用户
            session.setAttribute(SystemConstant.LOGINUSER, employe);
            objectHashMap.put(SystemConstant.SUCCESS, true);
        } else {
            objectHashMap.put(SystemConstant.SUCCESS, false);
            objectHashMap.put(SystemConstant.MESSAGES, "账号密码错误，请重新登录");
        }
        return JSON.toJSONString(objectHashMap);
    }

    /**
     * 员工数据查询列表
     *
     * @param employeeVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(EmployeeVo employeeVo) {
        // 设置分页
        PageHelper.startPage(employeeVo.getPage(), employeeVo.getLimit());
        // 调用查询方法
        List<Employee> employeeList = employeeService.findEmployeeList(employeeVo);
        // 创建分页对象
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeeList);
        return new DataGridViewResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 添加员工
     *
     * @param employee
     * @param session  获取到创建人
     * @return
     */
    @RequestMapping("/addEmployeeData")
    public String addEmployeeData(Employee employee, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (employeeService.queryEmployeeList(employee.getLoginName()) > 0) {
                map.put(SystemConstant.SUCCESS, false);
                map.put(SystemConstant.MESSAGES, "员工登录名称重复，不可添加");
                return JSON.toJSONString(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取创建人
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        // 设置创建人
        employee.setCreatedBy(loginUser.getId());
        if (employeeService.addEmployee(employee) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGES, "员工信息添加成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGES, "员工信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改员工数据
     *
     * @param employee
     * @return
     */
    @RequestMapping("/updateEmployeeData")
    public String updateEmployeeData(Employee employee, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取修改人
        Employee loginUser = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        // 设置修改人
        employee.setModifyBy(loginUser.getId());
        if (employeeService.updateEmployeeData(employee) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGES, "员工信息修改成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGES, "员工信息修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除员工数据
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteEmployeeData")
    public String deleteEmployeeData(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(id.equals(6)){
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGES, "超级管理员账号不可删除");
            return JSON.toJSONString(map);
        }
        if (employeeService.deleteById(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGES, "删除员工信息成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGES, "删除员工信息失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (employeeService.resetPwd(id) > 0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGES, "重置员工密码成功");
        } else {
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGES, "重置员工密码失败");
        }
        return JSON.toJSONString(map);
    }
}
