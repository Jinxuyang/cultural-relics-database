package com.fehead.culturalrelicsdatabase.controller;

import com.fehead.culturalrelicsdatabase.controller.viewobject.UserVo;
import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.EmBusinessError;
import com.fehead.culturalrelicsdatabase.core.response.CommonReturnType;
import com.fehead.culturalrelicsdatabase.entity.User;
import com.fehead.culturalrelicsdatabase.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zero
 * @Date 2021/6/18 21:54
 * @Since 1.8
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController extends BaseController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @PostMapping
    public CommonReturnType register(@RequestBody User userinfo) throws BusinessException {

        User user = userService.register(userinfo.getUsername(), userinfo.getPassword());

        if (user == null) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR, "注册失败");
        }

        return CommonReturnType.success("注册成功");
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_admin"})
    public CommonReturnType privilegeEscalation(@PathVariable String id) throws BusinessException {
        UpdateResult result = userService.privilegeEscalation(id);

        if (!result.wasAcknowledged()) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR, "更新失败");
        }
        return CommonReturnType.success("修改成功");
    }


    @DeleteMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType deleteUser(@RequestParam @NotBlank(message = "id不能为空") String id) throws BusinessException {
        DeleteResult result = mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "user");
        if (!result.wasAcknowledged()) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR, "删除失败");
        }
        return CommonReturnType.success("删除成功");
    }

    /**
     * 用户查询 分页 是否优化分页 需要哪些字段
     */
    @GetMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType searchUser(@RequestParam(required = false) String keyword,
                                       @RequestParam @NotNull(message = "page参数缺失") Integer page,
                                       @RequestParam @NotNull(message = "size参数缺失") Integer size) {
        if (keyword == null) keyword = "";
        Query query = new Query(Criteria.where("username").regex(keyword));
        query.skip((page - 1) * size).limit(size);
//        Field fields = query.fields();
//        fields.include("username");
//        fields.include("_id");
        Map<String,Object> map = new HashMap<>(2);
        List<UserVo> list = mongoTemplate.find(query, UserVo.class,"user");
        map.put("total",mongoTemplate.count(new Query(),"user"));
        map.put("list",list);
        return CommonReturnType.success(map, "查询结果");
    }
}