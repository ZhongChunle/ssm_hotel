package com.zcl.dao;

import com.zcl.entity.Employee;
import com.zcl.entity.vo.EmployeeVo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：员工登录查询接口
 *
 * @author zhong
 * @date 2022-05-15 12:02
 */
public interface EmployeeMapper {
    /**
     * 根据员工的登录信息查询账号
     * @param loginName
     * @return
     */
    Employee findEmployeeByLoginName(String loginName);

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
     * 删除员工角色关系
     * @param id
     */
    @Delete("delete from sys_role_employee where eid = #{id}")
    void deleteEmployeeAndRole(Integer id);
}
