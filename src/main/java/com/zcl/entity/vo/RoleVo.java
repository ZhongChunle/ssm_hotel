package com.zcl.entity.vo;

import com.zcl.entity.Role;

/**
 * 项目名称：ssm_hotel
 * 描述：分页的角色类
 *
 * @author zhong
 * @date 2022-05-16 16:28
 */
public class RoleVo extends Role {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页显示数量
     */
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
