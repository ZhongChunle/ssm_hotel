package com.zcl.entity.vo;

import com.zcl.entity.Employee;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 项目名称：ssm_hotel
 * 描述：部门分页信息实体类
 *
 * @author zhong
 * @date 2022-05-15 21:58
 */
public class EmployeeVo extends Employee {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页显示数量
     */
    private Integer limit;
    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
