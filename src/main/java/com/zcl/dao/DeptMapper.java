package com.zcl.dao;

import com.zcl.entity.Dept;
import com.zcl.entity.vo.DeptVo;

import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：部门信息接口
 *
 * @author zhong
 * @date 2022-05-15 21:55
 */
public interface DeptMapper {
    /**
     * 查询部门
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


    int deleteById(Integer id);

    List<Dept> findDeptList();
}
