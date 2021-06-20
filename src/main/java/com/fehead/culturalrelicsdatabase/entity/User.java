package com.fehead.culturalrelicsdatabase.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author Verge
 * @Date 2021/6/20 13:23
 * @Version 1.0
 */
@Data
public class User {
    private String id;

    private String username;

    private String password;

    private List<String> roles;

    private long createTime;

    private long updateTime;
}