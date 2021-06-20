package com.fehead.culturalrelicsdatabase.service;

import com.fehead.culturalrelicsdatabase.entity.User;
import com.mongodb.client.result.UpdateResult;

/**
 * @Author Zero
 * @Date 2021/6/19 19:45
 * @Since 1.8
 **/
public interface UserService {
    User register(String username,String password);

    UpdateResult privilegeEscalation(String id);
}