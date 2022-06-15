package com.zcl.entity;


/**
 * 项目名称：ssm_hotel
 * 描述：楼层实体
 *
 * @author zhong
 * @date 2022-05-19 20:04
 */
public class floor {
    private Integer id;
    private String name;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
