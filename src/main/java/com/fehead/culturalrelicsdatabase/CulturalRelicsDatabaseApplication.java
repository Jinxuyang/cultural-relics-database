package com.fehead.culturalrelicsdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@EnableRedisHttpSession
public class CulturalRelicsDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CulturalRelicsDatabaseApplication.class, args);
    }

}