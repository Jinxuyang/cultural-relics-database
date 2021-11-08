package com.fehead.culturalrelicsdatabase.config.handler;

import com.alibaba.fastjson.JSON;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zero
 * @Description: 权限拒绝处理逻辑
 * @Date Create in 2019/9/3 20:56
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        CommonReturnType result = CommonReturnType.fail("没有权限");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
