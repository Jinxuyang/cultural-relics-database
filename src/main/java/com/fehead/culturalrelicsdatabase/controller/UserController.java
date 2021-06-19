package com.fehead.culturalrelicsdatabase.controller;

import com.alibaba.fastjson.JSONObject;
import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import com.fehead.culturalrelicsdatabase.utils.MongoDBClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.util.*;


import java.util.List;

/**
 * @Author Zero
 * @Date 2021/6/18 21:54
 * @Since 1.8
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController extends BaseController{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private MongoDBClient mongoDBClient;

    @PostMapping("/register")
    public CommonReturnType register(@RequestParam("username")@NotBlank(message = "用户名不能为空") String username,
                                    @RequestParam("password")@NotBlank(message = "密码不能为空") String password) throws BusinessException {
        User user = userService.obtainUser(username, password);
        mongoTemplate.save(user, "user");
        return CommonReturnType.success("注册成功");
    }
    @PostMapping("/admin")
    @Secured({"ROLE_super_admin"})
    public CommonReturnType changeRole(@RequestParam("username")@NotBlank(message = "用户名不能为空") String username) {
        User user = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("username").is(username)), User.class, "user");
        user.setRole("admin");
        user.setUpdateTime(new Date());
        mongoTemplate.save(user,"user");
        return CommonReturnType.success("修改成功");
    }

    /**
     * 只有超级管理员可以增加管理员
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/add")
    @Secured({"ROLE_super_admin"})
    public CommonReturnType addAdmin(@RequestParam("username")@NotBlank(message = "用户名不能为空")String username,
                                     @RequestParam("password")@NotBlank(message = "密码不能为空") String password) {
        User user = userService.obtainAdmin(username, password);
        mongoTemplate.save(user, "user");
        return CommonReturnType.success("注册成功");
    }

    /**
     * 只有管理员可以删除用户
     * @param username
     * @return
     */
    @PostMapping("/delete")
    @Secured({"ROLE_admin","ROLE_super_admin"})
    public CommonReturnType deleteUser(@RequestParam("username")@NotBlank(message = "用户名不能为空")String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.fields().exclude("_id");
        query.fields().include("role");
        final String user = mongoTemplate.findOne(query, String.class, "user");
        log.info("删除的用户角色是:{}", user);
        if("user".equals(user)){
            mongoTemplate.remove(new Query().addCriteria(Criteria.where("username").is(username)),"user");
        } else {
            return CommonReturnType.fail("您的权限不够");
        }
        return CommonReturnType.success("删除成功");
    }

    /**
     * 只有超管可以删除管理员
     * @param username
     * @return
     */
    @PostMapping("/delete1")
    @Secured({"ROLE_super_admin"})
    public CommonReturnType deleteAdmin(@RequestParam("username")@NotBlank(message = "用户名不能为空")String username) {
        mongoTemplate.remove(new Query().addCriteria(Criteria.where("username").is(username)),"user");
        return CommonReturnType.success("删除成功");
    }
}

