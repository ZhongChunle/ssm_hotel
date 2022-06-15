package com.zcl.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcl.entity.Dept;
import com.zcl.entity.vo.DeptVo;
import com.zcl.service.DeptService;
import com.zcl.service.EmployeeService;
import com.zcl.utils.DataGridViewResult;
import com.zcl.utils.SystemConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：查询部门列表控制器
 *
 * @author zhong
 * @date 2022-05-16 6:36
 */
@RestController
@RequestMapping("/admin/dept")
public class DeptController {
    /**
     * 注入业务层
     */
    @Resource
    DeptService deptService;

    @Resource
    EmployeeService employeeService;

    /**
     * 查询部门信息
     * @param vo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(DeptVo vo){
        System.out.println("获取部门分页的数据："+vo);
        // 设置分页信息【必须放在前面】
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        // 调用查询方法
        List<Dept> deptList = deptService.findDeptListByPage(vo);
        // 创建分页对象
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(deptList);
        // 调用layui返回参数类返回数据[返回的总数，返回的数据]
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 添加部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/addDept")
    public String addDept(Dept dept) {
        System.out.println("接收添加部门信息："+dept);
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(deptService.addDept(dept) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"部门信息添加成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"部门信息添加失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/updateDept")
    public String updateDept(Dept dept) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(deptService.editDept(dept) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES,"部门信息修改成功");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES,"部门信息修改失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 判断是否删除部门信息
     * @param id
     * @return
     */
    @RequestMapping("/checkDeptHasEmployee")
    public String checkDeptHasEmployee(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(employeeService.getEmployeeCountByDeptId(id) > 0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGES, "该部门存在员工信息，无法删除");
        }else {
            map.put(SystemConstant.SUCCESS,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 根据部门id删除部门信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if(deptService.deleteById(id) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGES, "部门删除成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGES, "部门删除失败");
        }
        return JSON.toJSONString(map);
    }

    /**
     * 查询所有的部门列表信息
     * @return
     */
    @RequestMapping("/deptList")
    public String findDeptList(){
        return JSON.toJSONString(deptService.findDeptList());
    }
}
