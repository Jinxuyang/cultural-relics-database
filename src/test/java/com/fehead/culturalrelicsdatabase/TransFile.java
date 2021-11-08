package com.fehead.culturalrelicsdatabase;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.fehead.culturalrelicsdatabase.entity.ChemicalComposition;
import com.fehead.culturalrelicsdatabase.entity.EssentialInformation;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Verge
 * @Date 2021/11/8 12:44
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransFile {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        Object list = mongoTemplate.find(new Query(), Object.class, "Chemical_Composition");
        JSONArray jsonArray = new JSONArray(list);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = new JSONObject(obj);
            String id = (String) jsonObject.get("_id");


            Object list123 = mongoTemplate.find(new Query(Criteria.where("_id").is(id)), Object.class, "Chemical_Composition");
            JSONArray jsonArray123 = new JSONArray(list123);


            List<ChemicalComposition> chemicalCompositionList = jsonArray123.stream()
                    .map(JSONObject::new)
                    .map(o -> {
                        ChemicalComposition chemicalComposition = new ChemicalComposition();
                        chemicalComposition.setTestName((List<String>) o.get("Test_name"));
                        chemicalComposition.setFetusConstantElement((Map<String, Object>) o.get("Fetus_Constant_Element"));
                        chemicalComposition.setFetusTraceElement((Map<String, Object>) o.get("Fetus_Trace_Element"));
                        chemicalComposition.setGlazeConstantElement((Map<String, Object>) o.get("Glaze_Constant_Element"));
                        chemicalComposition.setGlazeTraceElement((Map<String, Object>) o.get("Glaze_Trace_Element"));
                        chemicalComposition.setTestUnit((List<String>) o.get("Test_Unit"));
                        return chemicalComposition;
                    })
                    .collect(Collectors.toList());

            Object list111 = mongoTemplate.find(new Query(Criteria.where("_id").is(id)), Object.class, "Essential_Information");
            JSONArray jsonArray111 = new JSONArray(list111);
            List<EssentialInformation> essentialInformationList = jsonArray111.stream()
                    .map(JSONObject::new)
                    .map(o -> {
                        EssentialInformation essentialInformation = new EssentialInformation();
                        essentialInformation.setKiln((String) o.get("Kiln"));
                        essentialInformation.setBurningAge((String) o.get("Burning_Age"));
                        essentialInformation.setCulturalClassification((String) o.get("Cultural_Classification"));
                        essentialInformation.setName((String) o.get("Name"));
                        essentialInformation.setOriginalNumber((String) o.get("Original_Number"));
                        essentialInformation.setSampleProvider((String) o.get("Sample_Provider"));
                        essentialInformation.setStorageLocation((String) o.get("Storage_Location"));
                        essentialInformation.setStoragePlace((String) o.get("Storage_Place"));
                        essentialInformation.setCollectionLocation((String) o.get("Collection_Location"));
                        return essentialInformation;
                    })
                    .collect(Collectors.toList());

            Relic relic = new Relic();
            relic.setId(id);
            relic.setChemicalComposition(chemicalCompositionList.get(0));
            relic.setEssentialInformation(essentialInformationList.get(0));

            mongoTemplate.save(relic, "relic");
            System.out.println(relic);
        }

    }
}
