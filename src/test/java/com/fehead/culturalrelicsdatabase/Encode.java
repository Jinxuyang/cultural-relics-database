package com.fehead.culturalrelicsdatabase;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Verge
 * @Date 2021/6/20 14:36
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Encode {
    @Autowired
    private SymmetricCrypto crypto;

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Test
    public void test(){
        for (int i = 0; i < 5; i++) {
            System.out.println(crypto.encryptBase64("123"));
        }
    }
}