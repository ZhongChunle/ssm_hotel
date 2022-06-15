package com.zcl.service.impl;

import com.zcl.dao.DeptMapper;
import com.zcl.entity.Dept;
import com.zcl.entity.vo.DeptVo;
import com.zcl.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：ssm_hotel
 * 描述：查询部门列表业务层实现类
 *
 * @author zhong
 * @date 2022-05-16 6:32
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Resource
    DeptMapper deptMapper;

    /**
     * 查询部门列表
     * @param deptVo
     * @return
     */
    public List<Dept> findDeptListByPage(DeptVo deptVo) {
        return deptMapper.findDeptListByPage(deptVo);
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    public int addDept(Dept dept) {
        // 提交时间
        dept.setCreateDate(new Date());
        return deptMapper.addDept(dept);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    public int editDept(Dept dept) {
        return deptMapper.editDept(dept);
    }

    public int deleteById(Integer id) {
        return deptMapper.deleteById(id);
    }


    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }
}
