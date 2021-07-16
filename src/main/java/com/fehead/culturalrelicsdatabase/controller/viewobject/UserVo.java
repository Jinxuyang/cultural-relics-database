package com.fehead.culturalrelicsdatabase.controller.viewobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author whirabbit
 * @Date 2021/6/21 17:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String id;

    private String username;

    private List<String> roles;
}
