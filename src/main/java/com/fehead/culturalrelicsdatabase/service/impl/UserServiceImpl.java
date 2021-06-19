package com.fehead.culturalrelicsdatabase.service.impl;

import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Zero
 * @Date 2021/6/19 19:45
 * @Since 1.8
 **/
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User obtainUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setRoles(new String[] {"user"});
        return user;
    }
}
