package com.fehead.culturalrelicsdatabase.controller;

import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.Relic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Verge
 * @Date 2021/6/20 14:18
 * @Version 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("api/v1/relic")
public class RelicController {
    MongoTemplate mongoTemplate;

    @Autowired
    public RelicController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/detail")
    public CommonReturnType getDetail(@RequestParam String id){
        System.out.println(mongoTemplate.findById(id,Relic.class));
       return CommonReturnType.success(mongoTemplate.findById(id,Relic.class));
    }
    /**
     * 查询 分页
     */
    @GetMapping
    @Secured({"ROLE_user"})
    public CommonReturnType searchRelic(@RequestParam(required = false) String keyword,
                                        @RequestParam@NotNull(message = "page参数缺失") Integer page,
                                        @RequestParam @NotNull(message = "size参数缺失") Integer size) {
        if (keyword == null) keyword = "";
        Query query = new Query(Criteria.where("name").regex(keyword));
        query.skip((page - 1) * size).limit(size);
        return CommonReturnType.success(mongoTemplate.find(query, Relic.class), "查询结果");
    }

}