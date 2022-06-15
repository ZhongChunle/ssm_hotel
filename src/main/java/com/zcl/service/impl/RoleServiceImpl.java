package com.zcl.service.impl;

import com.zcl.dao.EmployeeMapper;
import com.zcl.dao.RoleMapper;
import com.zcl.entity.Role;
import com.zcl.entity.vo.RoleVo;
import com.zcl.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-16 16:42
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;

    public List<Role> findRoleList(RoleVo vo) {
        return roleMapper.findRoleList(vo);
    }

    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    public int deleteRole(Integer id) {
        return roleMapper.deleteRole(id);
    }

    /**
     * 保存角色菜单关系
     * @param ids
     * @param roleId
     * @return
     */
    public int saveRoleMenu(String ids, Integer roleId) {
        try {
            // 根据角色id删除原有的菜单关系
            roleMapper.deleteRoleMenu(roleId);
            // 将ids拆分成数组
            String[] idsStr = ids.split(",");
            // 循环调用添加角色菜单关系
            for (int i = 0; i < idsStr.length; i++) {
                // 调用保存关系
                roleMapper.addRoleMenu(roleId,idsStr[i]);
            }
            return 1; // 成功
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Map<String, Object>> findRoleListByMap() {
        return roleMapper.findRoleListByMap();
    }

    /**
     * 根据员工id查询角色
     * @param employeeId
     * @return
     */
    public List<Integer> findEmployeeRoleByEmployeeId(Integer employeeId) {
        return roleMapper.findEmployeeRoleByEmployeeId(employeeId);
    }

    @Resource
    EmployeeMapper employeeMapper;
    /**
     * 保存角色分配
     * @param roleids
     * @param empId
     * @return
     */
    public boolean
    saveEmployeeRole(String roleids, Integer empId) {
        try {
            // 先删除员工角色关系数据
            employeeMapper.deleteEmployeeAndRole(empId);
            // 循环保存员工数据
            String[] idsStr = roleids.split(",");
            for (int i = 0; i < idsStr.length; i++) {
                roleMapper.addEmployeeRole(empId, idsStr[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
