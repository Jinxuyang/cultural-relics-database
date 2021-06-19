package com.fehead.culturalrelicsdatabase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.culturalrelicsdatabase.entity.Relic;
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
        Relic relic = new Relic();
        relic.setDescription("测试");
        relic.setName("测试");
        relic.setUnit("测试");


        System.out.println(template.save(relic));
    }

    /*@Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        objectMapper
    }*/
}