package com.zcl.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Checkout {

    private Long id;
    private String checkOutNumber;
    private Long checkInId;
    private Double consumePrice;
    private Date createDate;
    private Integer createdBy;
    private String remark;
}
