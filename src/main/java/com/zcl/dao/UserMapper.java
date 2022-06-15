package com.zcl.dao;

import com.zcl.entity.User;

/**
 * 项目名称：ssm_hotel
 * 描述：用户持久层
 *
 * @author zhong
 * @date 2022-05-21 14:47
 */
public interface UserMapper {
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
