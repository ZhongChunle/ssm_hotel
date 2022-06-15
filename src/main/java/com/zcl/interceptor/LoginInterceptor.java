package com.zcl.interceptor;

import com.zcl.utils.SystemConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称：ssm_hotel
 * 描述：登录拦截器
 *
 * @author zhong
 * @date 2022-05-15 17:05
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断session是否为空
        if(request.getSession().getAttribute(SystemConstant.LOGINUSER) == null){
            // 为空就是没有登录，跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/admin/login.html");
            // 验证失败进行拦截
            return false;
        }
        // 验证成功
        return true;
    }
}
