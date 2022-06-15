package com.zcl.service;

import com.zcl.entity.Role;
import com.zcl.entity.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：角色管理业务层接口
 *
 * @author zhong
 * @date 2022-05-16 16:41
 */
public interface RoleService {
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
     * 角色菜单分配
     * @param ids
     * @param roleId
     * @return
     */
    int saveRoleMenu(String ids, Integer roleId);

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
     * 保存角色分配
     * @param roleids
     * @param empId
     * @return
     */
    boolean saveEmployeeRole(String roleids, Integer empId);
}
