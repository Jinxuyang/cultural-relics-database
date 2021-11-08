package com.fehead.culturalrelicsdatabase.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author Verge
 * @Date 2021/11/8 12:31
 * @Version 1.0
 */
@Data
public class ChemicalComposition {
    private Map<String, Object> fetusConstantElement;
    private Map<String, Object> fetusTraceElement;
    private Map<String, Object> glazeConstantElement;
    private Map<String, Object> glazeTraceElement;
    private List<String> testName;
    private List<String> testUnit;
}
