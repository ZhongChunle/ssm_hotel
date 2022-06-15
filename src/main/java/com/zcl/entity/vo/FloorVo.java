package com.zcl.entity.vo;

import com.zcl.entity.Dept;
import com.zcl.entity.floor;

/**
 * 项目名称：ssm_hotel
 * 描述：部门分页信息实体类
 *
 * @author zhong
 * @date 2022-05-15 21:58
 */
public class FloorVo extends floor {
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
