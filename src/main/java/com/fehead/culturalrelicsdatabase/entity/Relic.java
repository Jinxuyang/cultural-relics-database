package com.fehead.culturalrelicsdatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Verge
 * @Date 2021/6/19 15:07
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relic {
    private String id;

    private String name;

    private String unit;

    private String description;
}