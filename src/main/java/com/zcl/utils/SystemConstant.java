package com.zcl.utils;

/**
 * 项目名称：ssm_hotel
 * 描述：常用的常量数据接口方便维护
 *
 * @author zhong
 * @date 2022-05-15 12:19
 */
public interface SystemConstant {
    /**
     * 密码加密次数常量
     */
    Integer PASSWORD_COUNT = 5;

    /**
     * 登录员工保存的key
     */
    String LOGINUSER = "loginUser";

    /**
     * 前台用户登录时保存的key
     */
    String FRONT_LOGINUSER = "currentUser";

    /**
     * 登录成功标志
     */
    String SUCCESS = "succser";

    /**
     * 登录失败标志
     */
    String MESSAGES = "messages";

    /**
     * 是否存在
     */
    String EXIST = "exist";

    /**
     * 默认的登录密码
     */
    String DEFAULT_LOGIN_PWD = "123456";

    /**
     * 默认的图片上传路径
     */
    String IMAGE_UPLOAD_PATH = "F:/Java项目案例（手动完成）/SSM项目/SSM酒店管理系统V1/Project/ssm_hotel/src/main/webapp/hotel/upload/";
}
