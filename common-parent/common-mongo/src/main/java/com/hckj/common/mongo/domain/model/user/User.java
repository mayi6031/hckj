package com.hckj.common.mongo.domain.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@ToString
@NoArgsConstructor
@Document(collection = "user")
public class User {
    private static final long serialVersionUID = 4552409445309519452L;

    @Id
    private String id;
    @Field("username")
    private String username;
    private String password;
    private String registerTime;
    private String phone;
    private String name;
    private String sex;
    private String age;

}

