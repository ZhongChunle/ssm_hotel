package com.zcl.dao;

import com.zcl.entity.Role;
import com.zcl.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：角色管理持久层
 *
 * @author zhong
 * @date 2022-05-16 16:32
 */
public interface RoleMapper {
    /**
     *
     * @return
     * @param vo
     */
    List<Role> findRoleList(RoleVo vo);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    int deleteRole(Integer id);

    /**
     * 根绝角色id删除原来的角色数据
     * @param roleId
     */
    @Delete("delete from sys_role_menu where rid = #{roleId}")
    void deleteRoleMenu(Integer roleId);

    /**
     * 添加保存角色和菜单数据
     * @param roleId
     * @param menus
     */
    @Insert("insert into sys_role_menu(mid,rid) values (#{menus},#{roleId})")
    void addRoleMenu(@Param("roleId") Integer roleId, @Param("menus") String menus);

    /**
     * 分配角色查询列表
     * @return
     */
    List<Map<String,Object>> findRoleListByMap();

    /**
     * 根据员工id查询员工列表
     * @return
     */
    List<Integer> findEmployeeRoleByEmployeeId(Integer employeeId);

    /**
     * 保存员工角色关系
     * @param empId
     * @param RoleId
     */
    @Insert("insert into sys_role_employee(eid,rid) values(#{eid},#{rid})")
    void addEmployeeRole(@Param("eid") Integer empId,@Param("rid") String RoleId);
}
