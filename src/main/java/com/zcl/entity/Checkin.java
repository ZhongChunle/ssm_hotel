package com.zcl.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Checkin{
    private Integer id;
    private Integer roomtypeid;
    private Integer roomid;
    private String customername;
    private String idcard;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivedate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leavedate;
    private BigDecimal payprice;
    private Integer status;
    private String remark;
    private Integer ordersid;
    private Integer createdby;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;
    private Integer modifyby;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifydate;
    private String room;
    private String roomType;
}