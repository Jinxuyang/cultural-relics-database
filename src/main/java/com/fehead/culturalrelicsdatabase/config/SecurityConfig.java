package com.fehead.culturalrelicsdatabase.config;

import com.fehead.culturalrelicsdatabase.config.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @Author Zero
 * @Date 2021/6/3 23:06
 * @Since 1.8
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userdetailservice;

    //未登录处理器（匿名访问无权限处理）
    @Autowired
    private CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    //会话过期策略处理器（异地登录）
    @Autowired
    private CustomizeSessionInformationExpiredStrategy customizeSessionInformationExpiredStrategy;

    //自定义用户登录方式过滤器
    /*@Autowired
    private UserNameAndJsonFilter userNameAndJsonFilter;*/

    //登录成功处理器
    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    //登录失败处理器
    @Autowired
    private CustomizeAuthenticationFailureHandler customizeAuthenticationFailureHandler;

    //权限拒绝处理器
    @Autowired
    private CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Autowired
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

    /**
     * 自定义数据库查寻认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userdetailservice).passwordEncoder(passwordEncoder());
    }

    /**
     * 设置加密方式
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 重写验证管理器用于认证方式
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 配置登录
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();//开启跨域以及关闭防护

        http.exceptionHandling().authenticationEntryPoint(customizeAuthenticationEntryPoint); //更改未登录或者登录过期默认跳转
        //
        http.formLogin()
            .loginProcessingUrl("/user/login");

        //放行路径
        http.authorizeRequests() //验证请求
            .antMatchers("/user/login").permitAll()//允许所有用户访问这个路径
            .antMatchers("/usr/add").hasAnyAuthority("admin")
            //.anyRequest().authenticated();
            .anyRequest().permitAll();
        //退出登录
        http.logout()
            //.permitAll() //允许所有用户操作
            .logoutUrl("/logout").logoutSuccessUrl("/test/hello").deleteCookies("JSESSIONID") //登出陈宫删除cookies
            .logoutSuccessHandler(customizeLogoutSuccessHandler) //登出成功逻辑处理
            .and().formLogin()
            /*.permitAll()*/ //允许所有用户操作
            .successHandler(customizeAuthenticationSuccessHandler) //登录成功逻辑处理
            .failureHandler(customizeAuthenticationFailureHandler) //登录失败逻辑处理
            .and()
            .exceptionHandling()
            .accessDeniedHandler(customizeAccessDeniedHandler) //权限拒绝逻辑处理
            .authenticationEntryPoint(customizeAuthenticationEntryPoint) //匿名访问无权限访问资源异常处理
            //会话管理
            .and().sessionManagement()
            .maximumSessions(1) //同一个用户最大的登录数量
            .expiredSessionStrategy(customizeSessionInformationExpiredStrategy); //异地登录（会话失效）处理逻辑

    }
}