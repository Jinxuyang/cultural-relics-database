package com.fehead.culturalrelicsdatabase.controller;

import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Verge
 * @Date 2021/11/16 21:16
 * @Version 1.0
 */
@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;

    @Test
    void register() throws BusinessException {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setRoles(Collections.singletonList("admin"));
        userController.register(user);
    }
}