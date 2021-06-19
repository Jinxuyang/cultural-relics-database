package com.fehead.culturalrelicsdatabase.config.service;

import com.fehead.culturalrelicsdatabase.utils.MongoDBClient;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Zero
 * @Date 2021/6/3 22:58
 * @Since 1.8
 **/
@Service("userdetailservice")
public class MyDetailService implements UserDetailsService {
    @Autowired
    private MongoDBClient mongoDBClient;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(null == username|| "".equals(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }

        final Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        com.fehead.culturalrelicsdatabase.entity.User user = mongoTemplate.findOne(query, com.fehead.culturalrelicsdatabase.entity.User.class, "user");
        System.out.println(user);
        //这里通过查找获取权限
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");

        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }
}
