package com.zcl.service.impl;

import com.zcl.dao.UserMapper;
import com.zcl.entity.User;
import com.zcl.service.UserService;
import com.zcl.utils.PasswordUtil;
import com.zcl.utils.SystemConstant;
import com.zcl.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 项目名称：ssm_hotel
 * 描述：
 *
 * @author zhong
 * @date 2022-05-21 14:50
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * 注入持久层
     */
    @Resource
    UserMapper userMapper;

    /**
     * 根据用户名称查询登录
     * @param loginName
     * @param password
     * @return
     */
    public User login(String loginName, String password) {
        // 调用查询用户信息
        User loginUser = userMapper.findUserByName(loginName);
        // 判断查询的数据是否为空
        if(loginUser!=null){
            // 获取查询数据的盐对密码进行加密
            String newPassword = PasswordUtil.md5(password,loginUser.getSalt(),SystemConstant.PASSWORD_COUNT);
            // 比较密码是否正确
            if(loginUser.getPassword().equals(newPassword)){
                return loginUser;
            }
        }
        return null;
    }

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    /**
     * 根据用户名信息查询数据
     * @param loginName
     * @return
     */
    public User findUserByName(String loginName) {
        return userMapper.findUserByName(loginName);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public int AddUsers(User user) {
        // 自动生成盐值
        user.setSalt(UUIDUtils.randomUUID());
        // 密码加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(), SystemConstant.PASSWORD_COUNT));
        return userMapper.AddUsers(user);
    }
}
