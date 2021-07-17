package com.fehead.culturalrelicsdatabase;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.fehead.culturalrelicsdatabase.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Test
    public void test() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String token = null;
        final Claims claims = jwtTokenUtil.parseJwtRsa256(token);
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
    }
}