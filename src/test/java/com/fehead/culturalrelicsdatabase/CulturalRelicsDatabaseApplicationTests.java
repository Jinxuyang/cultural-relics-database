package com.fehead.culturalrelicsdatabase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CulturalRelicsDatabaseApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("test", "value");
    }
    @Value("${serve.host}")
    private String host;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void tes() {
        System.out.println(mongoTemplate.collectionExists("cultural_relics"));
    }
}