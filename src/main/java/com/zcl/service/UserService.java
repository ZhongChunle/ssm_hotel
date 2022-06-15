package com.zcl.service;

import com.zcl.entity.User;

/**
 * 项目名称：ssm_hotel
 * 描述：用户业务层
 *
 * @author zhong
 * @date 2022-05-21 14:50
 */
public interface UserService {
    /**
     * 根据用户名称进行查询登录
     * @param loginName
     * @param password
     * @return
     */
    User login(String loginName,String password);

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    User findUserByPhone(String phone);

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    User findUserByName(String loginName);

    /**
     * 注册用户数据
     * @param user
     * @return
     */
    int AddUsers(User user);
}
