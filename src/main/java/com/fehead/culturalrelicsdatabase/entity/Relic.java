package com.fehead.culturalrelicsdatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String unearthNumber;

    private String testNumber;

    private String unearthPlace;

    private String age;

    private String times;

    private String name;

    private List<CeramicElement> testResult;


}