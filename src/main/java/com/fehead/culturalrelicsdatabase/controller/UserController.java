package com.fehead.culturalrelicsdatabase.controller;

import com.alibaba.fastjson.JSONObject;
import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import java.util.List;

/**
 * @Author Zero
 * @Date 2021/6/18 21:54
 * @Since 1.8
 **/
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public CommonReturnType register(@RequestParam("username")String username,
                                    @RequestParam("password")  String password) throws BusinessException {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"用户或者密码为空");
        }
        User user = userService.obtainUser(username, password);

        return CommonReturnType.success(null);
    }
}

