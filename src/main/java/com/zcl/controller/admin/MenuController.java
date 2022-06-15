package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Employee;
import com.zcl.entity.Menu;
import com.zcl.entity.vo.MenuVo;
import com.zcl.service.MenuService;
import com.zcl.utils.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：查询菜单控制器
 *
 * @author zhong
 * @date 2022-05-15 19:34
 */
@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    /**
     * 注入业务层的接口
     */
    @Resource
    MenuService menuService;

    /**
     * 首页加载菜单
     * @return
     */
    @RequestMapping("/loadMenuList")
    public String loadMenuList(HttpSession session) {
        // 调用查询所有菜单的业务层方法
        // List<Menu> menuList = menuService.findMenuList();

        // ----- 根据员工id查询菜单信息修改如下
        Employee employee = (Employee) session.getAttribute(SystemConstant.LOGINUSER);
        List<Menu> menuList = menuService.findMenuListByEmployeeId(employee.getId());


        // 创建集合，保存菜单关系
        List<MenuNode> menuNodeList = new ArrayList<MenuNode>();
        // 遍历循环菜单（目的是菜单创建之间的层级关系）
        for (Menu menu : menuList) {
            // 创建菜单节点
            MenuNode menuNode = new MenuNode();
            menuNode.setId(menu.getId());
            menuNode.setPid(menu.getPid());
            menuNode.setHref(menu.getHref());
            menuNode.setTitle(menu.getTitle());
            menuNode.setIcon(menu.getIcon());
            menuNode.setTarget(menu.getTarget());
            menuNode.setSpread(menu.getSpread());
            menuNodeList.add(menuNode);
        }
        // 创建Map集合，保存menuInfo菜单信息
        LinkedHashMap<String, Object> menuInfo = new LinkedHashMap<String, Object>();
        // 创建Map集合，保存homeInfo信息
        LinkedHashMap<String, Object> homeInfo = new LinkedHashMap<String, Object>();
        // 创建Map集合，保存logoInfo信息
        LinkedHashMap<String, Object> logoInfo = new LinkedHashMap<String, Object>();
        // 保存homeInfo信息
        homeInfo.put("title","首页");
        homeInfo.put("href","/admin/desktop");
        // 保存logoInfo信息
        logoInfo.put("title","酒店管理系统");
        logoInfo.put("image","/statics/layui/images/logo.png");
        logoInfo.put("href","/admin/home.html");

        // 保存menuInfo菜单信息【最麻烦和重点】
        menuInfo.put("menuInfo", TreeUtil.toTree(menuNodeList,0));
        menuInfo.put("homeInfo", homeInfo);
        menuInfo.put("logoInfo", logoInfo);
        return JSON.toJSONString(menuInfo);
    }

    /**
     * 加载菜单管理的菜单树
     * @return
     */
    @RequestMapping("/loadMenuTree")
    public DataGridViewResult loadMenuTree(){
        // 调用查询菜单列表方法
        List<Menu> menuList = menuService.findMenuList();
        // 创建集合保存节点信息
        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();
        // 循环遍历菜单
        for (Menu menu : menuList) {
            // 判断当前是否展开
            Boolean spread = (menu.getSpread() == null || menu.getSpread() == 1) ? true:false;
            // 将菜单信息保存到treeNodes集合中
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
        // 返回数据
        return new DataGridViewResult(treeNodes);
    }

    /**
     * 查询菜单列表
     * @param menuVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(MenuVo menuVo){
        // 设置分页
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        // 调用重新菜单方法
        List<Menu> menuList = menuService.findMenuByPage(menuVo);
        // 创建分页对象
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
        // 返回数据【总数，列表数据】
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加菜单数据
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public String addMenu(Menu menu){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用查询方法
        if(menuService.addMenu(menu) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"菜单信息添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"菜单信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 编辑菜单数据
     * @param menu
     * @return
     */
    @RequestMapping("/updateMenu")
    public String updateMenu(Menu menu){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用查询方法
        if(menuService.updateMenu(menu) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"菜单信息编辑成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"菜单信息编辑失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 删除菜单数据
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenuById")
    public String deleteMenuById(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用查询方法
        if(menuService.deleteMenuById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"菜单信息删除成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"菜单信息删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据id判断是否有子菜单
     * @param id
     * @return
     */
    @RequestMapping("/checkMenuHasChild")
    public String checkMenuHasChild(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 调用查询方法
        if(menuService.getMenuCountByMenuid(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES,"当前菜单下有子菜单，不可删除哦");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }
}
