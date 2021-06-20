package com.fehead.culturalrelicsdatabase.controller;

import com.fehead.culturalrelicsdatabase.core.error.BusinessException;
import com.fehead.culturalrelicsdatabase.core.error.CommonError;
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
import java.util.Date;

/**
 * @Author Zero
 * @Date 2021/6/18 21:54
 * @Since 1.8
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController extends BaseController{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserService userService;

    @PostMapping
    public CommonReturnType register(@RequestParam("username")@NotBlank(message = "用户名不能为空") String username,
                                     @RequestParam("password")@NotBlank(message = "密码不能为空") String password) throws BusinessException {

        User user = userService.register(username, password);

        if (user == null) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"注册失败");
        }

        return CommonReturnType.success("注册成功");
    }

    @PutMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType privilegeEscalation(@RequestParam @NotBlank(message = "ID不能为空") String id) throws BusinessException {
        UpdateResult result = userService.privilegeEscalation(id);

        if (!result.wasAcknowledged()) {
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"更新失败");
        }
        return CommonReturnType.success("修改成功");
    }


    @DeleteMapping
    @Secured({"ROLE_admin"})
    public CommonReturnType deleteUser(@RequestParam @NotBlank(message = "id不能为空") String id) throws BusinessException {
        DeleteResult result = mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), "user");
        if (!result.wasAcknowledged()){
            throw new BusinessException(EmBusinessError.PRIMARY_ERROR,"删除失败");
        }
        return CommonReturnType.success("删除成功");
    }

}