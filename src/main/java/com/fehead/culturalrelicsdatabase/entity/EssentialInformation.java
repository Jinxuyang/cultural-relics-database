package com.fehead.culturalrelicsdatabase.entity;

import lombok.Data;

/**
 * @Author Verge
 * @Date 2021/11/8 12:38
 * @Version 1.0
 */
@Data
public class EssentialInformation {
    private String originalNumber;
    private String Name;
    private String culturalClassification;
    private String burningAge;
    private String kiln;
    private String sampleProvider;
    private String storagePlace;
    private String storageLocation;
    private String collectionLocation;
}
