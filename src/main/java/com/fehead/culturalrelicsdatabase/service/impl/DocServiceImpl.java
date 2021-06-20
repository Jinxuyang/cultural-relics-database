package com.fehead.culturalrelicsdatabase.service.impl;

import com.fehead.culturalrelicsdatabase.entity.Relic;
import com.fehead.culturalrelicsdatabase.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Verge
 * @Date 2021/6/19 16:02
 * @Version 1.0
 */
@Service
public class DocServiceImpl implements DocService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Relic> getDocsByIds(List<String> ids) {
        return mongoTemplate.find(new Query(Criteria.where("_id").in(ids)),Relic.class,"relic");
    }

    @Override
    public boolean saveRelics(List<Relic> list) {
        for (Relic relic : list) {
            mongoTemplate.save(relic,"relic");
        }
        return true;
    }
}