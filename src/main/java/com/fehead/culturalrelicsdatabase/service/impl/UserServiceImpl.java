package com.fehead.culturalrelicsdatabase.service.impl;

import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Zero
 * @Date 2021/6/19 19:45
 * @Since 1.8
 **/
@Service
public class UserServiceImpl implements UserService {
    User user = new User();
    @Override
    public User obtainUser(String username, String password) {

        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setRole("user");
        return user;
    }

    @Override
    public User obtainAdmin(String username, String password) {
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setRole("admin");
        return user;
    }
}
