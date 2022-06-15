package com.zcl.service.impl;

import com.zcl.dao.MenuMapper;
import com.zcl.entity.Menu;
import com.zcl.entity.vo.MenuVo;
import com.zcl.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-15 19:31
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMappere;

    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> findMenuList() {
        return menuMappere.findMenuList();
    }

    /**
     * 根据角色id查询菜单信息
     * @param roleid
     * @return
     */
    public List<Integer> findMenuListByRoleId(int roleid) {
        return menuMappere.findMenuListByRoleId(roleid);
    }

    /**
     * 根据菜单id查询菜单信息
     * @param menuListByRoleId
     */
    public List<Menu> findMenuByMenuid(List<Integer> menuListByRoleId) {
        return menuMappere.findMenuByMenuid(menuListByRoleId);
    }

    /**
     * 重新菜单列表
     * @param menuVo
     * @return
     */
    public List<Menu> findMenuByPage(MenuVo menuVo) {
        return menuMappere.findMenuByPage(menuVo);
    }

    /**
     * 添加菜单数据
     * @param menu
     * @return
     */
    public int addMenu(Menu menu) {
        if(menu.getPid() == null){
            menu.setPid(0);
        }
        return menuMappere.addMenu(menu);
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    public int updateMenu(Menu menu) {
        return menuMappere.updateMenu(menu);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    public int deleteMenuById(Integer id) {
        return menuMappere.deleteMenuById(id);
    }

    /**
     * 根据菜单id查询是否有子菜单
     * @param id
     * @return
     */
    public int getMenuCountByMenuid(Integer id) {
        return menuMappere.getMenuCountByMenuid(id);
    }

    /**
     * 根据员工id查询角色菜单信息
     * @param employeeId
     * @return
     */
    public List<Menu> findMenuListByEmployeeId(Integer employeeId) {
        return menuMappere.findMenuListByEmployeeId(employeeId);
    }
}
