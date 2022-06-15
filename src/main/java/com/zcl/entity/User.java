package com.zcl.entity;

import lombok.Data;

import java.util.Date;

/**
 * 项目名称：ssm_hotel
 * 描述：用户实体
 *
 * @author zhong
 * @date 2022-05-21 14:41
 */
@Data
public class User {
    /**
     * 编号
     */
    private Long id;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 用户状态
     */
    private Integer status;
    /**
     * 注册时间
     */
    private Date createDate;
    /**
     * 密码加密盐值
     */
    private String salt;

}
