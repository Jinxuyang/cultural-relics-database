package com.fehead.culturalrelicsdatabase.service;

import com.fehead.culturalrelicsdatabase.entity.User;

/**
 * @Author Zero
 * @Date 2021/6/19 19:45
 * @Since 1.8
 **/
public interface UserService {
    User obtainUser(String username,String password);
}
