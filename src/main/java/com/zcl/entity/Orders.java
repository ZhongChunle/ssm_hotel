package com.zcl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class Orders {
    private Integer id;//订单主键
    private String orderSno;//订单号
    private Integer accountId;//用户id
    private Integer roomTypeId;//房型ID
    private Integer roomId;//房间ID
    private String reservationName;//预订人姓名
    private String idCard;//身份证号码
    private String phone;//电话
    private Integer status;//订单状态 1-待确认 2-已确认
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveDate;//预订时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arriveDate;//入住时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;//离店时间
    private Double reservePrice;//预订房价
    private String remark;//备注
    //房间对象
    private String roomNum;
    //房型对象
    private String typeName;

}
