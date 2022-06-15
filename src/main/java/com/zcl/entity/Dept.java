package com.zcl.entity;

import java.util.Date;

/**
 * 项目名称：ssm_hotel
 * 描述：部门表实体类
 *
 * @author zhong
 * @date 2022-05-15 21:51
 */
public class Dept {
    private Integer id;
    private String deptname;
    private String address;
    private Date createDate;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptname='" + deptname + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
