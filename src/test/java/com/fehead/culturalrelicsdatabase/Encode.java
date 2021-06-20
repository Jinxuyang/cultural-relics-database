package com.fehead.culturalrelicsdatabase;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author Verge
 * @Date 2021/6/20 14:36
 * @Version 1.0
 */
public class Encode {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}