package com.zcl.service;

import com.zcl.entity.Menu;
import com.zcl.entity.vo.MenuVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：查询菜单列表业务层
 *
 * @author zhong
 * @date 2022-05-15 19:30
 */
public interface MenuService {
    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> findMenuList();

    /**
     * 根据角色id查询菜单id
     * @param roleid
     * @return
     */
    List<Integer> findMenuListByRoleId(int roleid);

    /**
     *  根据菜单id查询菜单信息
     * @param menuListByRoleId
     */
    List<Menu> findMenuByMenuid(List<Integer> menuListByRoleId);

    /**
     * 查询菜单列表
     * @param menuVo
     * @return
     */
    List<Menu> findMenuByPage(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    int addMenu(Menu menu);

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);

    /**
     * 根据id删除菜单
     * @param id
     * @return
     */
    int deleteMenuById(Integer id);

    /**
     * 根据菜单id查询是否有子菜单
     * @param id
     * @return
     */
    int getMenuCountByMenuid(Integer id);

    /**
     * 根据员工的id查询角色权限菜单
     * @param employeeId
     * @return
     */
    List<Menu> findMenuListByEmployeeId(Integer employeeId);
}
