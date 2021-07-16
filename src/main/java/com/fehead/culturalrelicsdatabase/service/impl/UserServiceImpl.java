package com.fehead.culturalrelicsdatabase.service.impl;

import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Zero
 * @Date 2021/6/19 19:45
 * @Since 1.8
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UpdateResult privilegeEscalation(String id) {
        List<String> roles = Arrays.asList("user","admin");

        return mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)), Update.update("roles", roles), "user");
    }

    @Override
    public User register(String username, String rawPassword) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(rawPassword));
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setRoles(Collections.singletonList("user"));

        return mongoTemplate.save(user,"user");

    }
}