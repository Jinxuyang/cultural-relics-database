package com.fehead.culturalrelicsdatabase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

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
    private String username;
    @Field("pwd")
    private String password;

    private String role;
}
