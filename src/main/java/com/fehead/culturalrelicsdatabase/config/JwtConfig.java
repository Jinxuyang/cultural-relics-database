package com.fehead.culturalrelicsdatabase.config;

import com.fehead.culturalrelicsdatabase.interceptor.JwtIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Zero
 * @Date 2021/7/17 17:55
 * @Since 1.8
 * @Description
 **/
public class JwtConfig implements WebMvcConfigurer {

    @Autowired
    private JwtIntercepter jwtIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/user/login",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-ui.html",
                        "/error");
    }
}
