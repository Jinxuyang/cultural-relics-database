package com.fehead.culturalrelicsdatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Zero
 * @Date 2021/6/19 0:40
 * @Since 1.8
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user") //类名的小写和集合名相等可以省略
public class User implements Serializable {
    @Id
    private String id;
    @Field("username") //指定属性名对应文档中的属性名
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Field("pwd")
    @NotBlank(message = "密码不能为空")
    private String password;

    private Date createTime;

    private Date updateTime;
    @Field("role")
    private String role;

}
