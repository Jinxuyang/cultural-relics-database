package com.fehead.culturalrelicsdatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;


@SpringBootTest
class CulturalRelicsDatabaseApplicationTests {


    private MongoTemplate mongoTemplate;

    @Test
    void test() {

        System.out.println(mongoTemplate.collectionExists("cultural_relics"));
    }
}