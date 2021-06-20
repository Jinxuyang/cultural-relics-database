package com.fehead.culturalrelicsdatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import com.fehead.culturalrelicsdatabase.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Verge
 * @Date 2021/6/19 15:20
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMongoDB {
    @Autowired
    private MongoTemplate template;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void find() throws IOException {
        OutputStream outputStream = new FileOutputStream("1.json");
        objectMapper.writeValue(outputStream,template.findAll(Relic.class));
        System.out.println(template.findAll(Relic.class));
    }
    @Test
    public void save() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("$2a$10$weMxpp68heA/v6NFruHpB.u2xSPKCgbr205hhctN7E596tq64mlhq");
        user.setRoles(Arrays.asList("user","admin"));

        System.out.println(template.save(user));

    }

    /*@Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        objectMapper
    }*/
}