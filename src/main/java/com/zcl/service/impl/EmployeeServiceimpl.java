package com.zcl.service.impl;

import com.zcl.dao.EmployeeMapper;
import com.zcl.entity.Employee;
import com.zcl.entity.vo.EmployeeVo;
import com.zcl.service.EmployeeService;
import com.zcl.utils.PasswordUtil;
import com.zcl.utils.SystemConstant;
import com.zcl.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：实现员工业务层
 *
 * @author zhong
 * @date 2022-05-15 12:13
 */
@Service
@Transactional
public class EmployeeServiceimpl implements EmployeeService {
    @Resource
    EmployeeMapper employe;

    /**
     * 员工登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    public Employee login(String loginName, String loginPwd) {
        // 1、根据账号查询员工信息方法
        Employee employeeByLoginName = employe.findEmployeeByLoginName(loginName);
        // 2、判断数据是否为空
        if(employeeByLoginName!= null){
            // 将密码进行加密对比，后面的加密次数使用的外部的接口声明，方便后面的修改
            String namePassword = PasswordUtil.md5(loginPwd, employeeByLoginName.getSalt(), SystemConstant.PASSWORD_COUNT);
            // 比较密码是否一致
            if(employeeByLoginName.getLoginPwd().equals(namePassword)){
                // 登录成功
                return employeeByLoginName;
            }
        }
        // 登录失败
        return null;
    }

    /**
     * 根据部门id查询员工数据
     * @param deptId
     * @return
     */
    public int getEmployeeCountByDeptId(Integer deptId) {
        return employe.getEmployeeCountByDeptId(deptId);
    }

    /**
     * 根据角色id查被员工使用的数量
     * @param roleId
     * @return
     */
    public int getEmployeeCountByRoleId(Integer roleId) {
        return employe.getEmployeeCountByRoleId(roleId);
    }

    /**
     * 查询员工列表
     * @param employeeVo
     * @return
     */
    public List<Employee> findEmployeeList(EmployeeVo employeeVo) {
        return employe.findEmployeeList(employeeVo);
    }

    /**
     * 根据员工登录名查询员工信息
     * @param employename
     * @return
     */
    public int queryEmployeeList(String employename) {
        return employe.queryEmployeeList(employename);
    }

    /**
     * 添加员工数据
     * @param employee
     * @return
     */
    public int addEmployee(Employee employee) {
        // 创建加密的盐
        employee.setSalt(UUIDUtils.randomUUID());
        // 创建时间
        employee.setCreateDate(new Date());
        // 密码加密【加密的密码，加密的盐，加密的位数】
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        return employe.addEmployee(employee);
    }

    /**
     * 编辑业务层
     * @param employee
     * @return
     */
    public int updateEmployeeData(Employee employee) {
        // 修改时间
        employee.setModifyDate(new Date());
        return employe.updateEmployeeData(employee);
    }

    /**
     * 删除员工数据
     * @param id
     * @return
     */
    public int deleteById(Integer id) {
        employe.deleteEmployeeAndRole(id);
        return employe.deleteById(id);
    }

    /**
     * 重置员工密码
     * @param id
     * @return
     */
    public int resetPwd(Integer id) {
        Employee employee = new Employee();
        employee.setId(id);
        // 设置盐值
        employee.setSalt(UUIDUtils.randomUUID());
        // 重置密码
        employee.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,employee.getSalt(),SystemConstant.PASSWORD_COUNT));
        System.out.println("重置密码需要的参数："+employee);
        return employe.updateEmployeeData(employee);
    }
}
