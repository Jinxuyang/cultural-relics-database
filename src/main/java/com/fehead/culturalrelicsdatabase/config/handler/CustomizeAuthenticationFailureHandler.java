package com.fehead.culturalrelicsdatabase.config.handler;

import com.alibaba.fastjson.JSON;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zero
 * @Description: 登录失败处理逻辑
 * @Date Create in 2019/9/3 15:52
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
        CommonReturnType result = null;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = CommonReturnType.fail("账号过期");
        } else if (e instanceof InternalAuthenticationServiceException) {
            //密码错误
            result = CommonReturnType.fail("用户不存在");
        } else if(e instanceof BadCredentialsException) {
            //用户不存在
            result = CommonReturnType.fail("账号或者密码错误");
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = CommonReturnType.fail("密码过期");
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = CommonReturnType.fail("账号不可用");
        } else if (e instanceof LockedException) {
            //账号锁定
            result = CommonReturnType.fail("账号锁定");
        } else if(e instanceof AuthenticationException){
            //其他错误
            result = CommonReturnType.fail("请求方法异常");
        } else if(e instanceof NonceExpiredException) {

            result = CommonReturnType.fail("异地登录");
        } else if(e instanceof SessionAuthenticationException) {

            result = CommonReturnType.fail("session错误");
        } else {

            result = CommonReturnType.fail("未知异常");
        }
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
