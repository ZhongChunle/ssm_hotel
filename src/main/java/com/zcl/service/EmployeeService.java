package com.zcl.service;

import com.zcl.entity.Employee;
import com.zcl.entity.vo.EmployeeVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：员工登录业务逻辑层接口
 *
 * @author zhong
 * @date 2022-05-15 12:11
 */
public interface EmployeeService {
    /**
     * 员工登录
     * @param loginName
     * @param loginPwd
     * @return
     */
    Employee login(String loginName,String loginPwd);

    /**
     * 根据部门id查询员工数据
     * @param deptId
     * @return
     */
    int getEmployeeCountByDeptId(Integer deptId);

    /**
     * 根据角色id查被员工使用的数量
     * @param roleId
     * @return
     */
    int getEmployeeCountByRoleId(Integer roleId);

    /**
     * 查询员工列表
     * @param employeeVo
     * @return
     */
    List<Employee> findEmployeeList(EmployeeVo employeeVo);

    /**
     * 根据登录名重新员工信息
     * @param employename
     * @return
     */
    int queryEmployeeList(String employename);

    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 修改用户数据
     * @param employee
     * @return
     */
    int updateEmployeeData(Employee employee);

    /**
     * 根据员工id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 重置员工密码
     * @param id
     * @return
     */
    int resetPwd(Integer id);
}
