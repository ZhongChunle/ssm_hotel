package com.zcl.service;

import com.zcl.entity.Dept;
import com.zcl.entity.vo.DeptVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：部门信息业务层接口
 *
 * @author zhong
 * @date 2022-05-16 6:31
 */
public interface DeptService {
    /**
     * 查询部门列表接口
     * @param deptVo
     * @return
     */
    List<Dept> findDeptListByPage(DeptVo deptVo);

    /**
     * 添加部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    int editDept(Dept dept);

    /**
     * 根据部门id删除部门信息
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 查询部门信息
     * @return
     */
    List<Dept> findDeptList();
}
