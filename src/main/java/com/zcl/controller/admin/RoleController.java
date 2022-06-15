package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Menu;
import com.zcl.entity.Role;
import com.zcl.entity.Role;
import com.zcl.entity.vo.RoleVo;
import com.zcl.service.EmployeeService;
import com.zcl.service.MenuService;
import com.zcl.service.RoleService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import com.zcl.utils.TreeNode;
import org.apache.ibatis.annotations.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：ssm_hotel
 * 描述：角色管理控制器
 *
 * @author zhong
 * @date 2022-05-16 16:44
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Resource
    RoleService roleService;
    @Resource
    EmployeeService employeeService;

    /**
     * 分页查询角色列表
     * @param roleVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoleVo roleVo) {
        // 设置分页
        PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        // 设置返回值
        List<Role> roleList = roleService.findRoleList(roleVo);
        // 封装分页数据
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @RequestMapping("/addRole")
    public String addRole(Role role) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roleService.addRole(role) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"角色信息添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"角色信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @RequestMapping("/updtaeRole")
    public String updtaeRole(Role role) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roleService.updateRole(role) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"角色信息修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"角色信息修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 判断是否删除角色信息
     * @param id
     * @return
     */
    @RequestMapping("/checkRoleHasEmployee")
    public String checkRoleHasEmployee(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(employeeService.getEmployeeCountByDeptId(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES, "该角色已赋值员工使用，无法删除");
        }else {
            map.put(SystemConstant.SUCCESS,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(roleService.deleteRole(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"角色信息删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"角色信息删除失败");
        }
        return JSON.toJSONString(map);
    }

    @Resource
    MenuService menuService;
    /**
     * 查询菜单数节点返回
     * @return
     */
    @RequestMapping("/initMenuTree")
    public DataGridViewResult initMenuTree(Integer roleId){
        // 1、查询所有的菜单信息
        List<Menu> menuList = menuService.findMenuList();
        // 2、根据角色id查询已拥有的菜单信息
        List<Integer> menuListByRoleId = menuService.findMenuListByRoleId(roleId);
        // 3、创建临时变量存储
        List<Menu> currentMenus = new ArrayList<Menu>();
        // 判断集合是否有菜单id
        if(menuListByRoleId != null && menuListByRoleId.size() > 0){
            // 根据菜单id查询菜单信息【对比已有菜单】
            currentMenus = menuService.findMenuByMenuid(menuListByRoleId);
        }

        // 创建集合保存树节点信息
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        // 循环菜单遍历所有菜单
        for (Menu menu : menuList) {
            // 定义变量，是否被选择状态，0表示不选择，1表示选择
            String checkArr = "0";
            // 内层循环遍历当前角色拥有的权限菜单
            for (Menu currentMenu : currentMenus) {
                // 判断菜单id是否相等
                if(currentMenu.getId().equals(menu.getId())){
                    checkArr = "1";
                    break;
                }
            }
            // 定义变量，表示是否展开
            boolean spread = (menu.getSpread() == null || menu.getSpread() == 1)?true:false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread,checkArr));
        }
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 分配菜单给角色
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/saveRoleMenu")
    public String saveRoleMenu(String ids,Integer roleId){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用保存菜单分配信息
        if(roleService.saveRoleMenu(ids,roleId)>0){
            map.put("message","角色菜单分配成功");
        }else{
            map.put("message","角色菜单分配失败");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping("/initRoleListByEmpId")
    public DataGridViewResult initRoleListByEmpId(Integer id){
        // 查询所有的角色列表
        List<Map<String, Object>> roleListByMap = roleService.findRoleListByMap();
        // 根据员工id查询拥有的角色信息
        List<Integer> roleIdList = roleService.findEmployeeRoleByEmployeeId(id);
        // 循环遍历比较两个集合中的角色id是否相同，相同的就选中角色复选框
        for (Map<String, Object> map : roleListByMap) {
            // 定义一个变量，标识是否选中状态
            boolean flag = false;
            // 获取每一个角色的id主键
            Integer rid = (Integer) map.get("id");
            // 内循环遍历集合中是否存储角色id相同的
            for (Integer roleId : roleIdList) {
                // 判断两个角色id是否相等
                if(rid.equals(roleId)){
                    flag = true;
                    break;
                }
            }
            // 将状态信息保存在map集合中返回,LAY_CHECKED是必须的
            map.put("LAY_CHECKED",flag);
        }

        return new DataGridViewResult(Long.valueOf(roleListByMap.size()),roleListByMap);
    }

    /**
     * 分配角色信息保存
     * @return
     */
    @RequestMapping("/saveEmployeeRole")
    public String saveEmployeeRole(String roleids,Integer empId){
        Map<String, Object> map = new HashMap<String, Object>();
        // 调用保存方法
        if(roleService.saveEmployeeRole(roleids,empId)){
            map.put(SystemConstant.MESSAGES,"角色分配保存成功");
        }else {
            map.put(SystemConstant.MESSAGES,"角色分配保存失败");
        }
        return JSON.toJSONString(map);
    }
}
