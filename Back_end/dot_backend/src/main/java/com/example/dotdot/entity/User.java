package com.example.dotdot.entity;

import afu.org.checkerframework.checker.igj.qual.I;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private String username;
    @JSONField(ordinal =2)
    private String password;
    @JSONField(ordinal =3)
    private Integer user_type;
    @JSONField(ordinal =4)
    private String email;
    @JSONField(ordinal =5)
    private boolean user_valid;

    @JSONField(ordinal =6)
//    @LazyCollection(LazyCollectionOption.TRUE)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String image;

    @JSONField(ordinal =7)
    private Integer account;

    public User(){

    }

    public User(int id, String username, String password,String email, Integer user_type, boolean user_valid,String image,Integer account) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.user_type = user_type;
        this.user_valid = user_valid;
        this.image = image;
        this.account = account;
    }

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getuser_type() {
        return user_type;
    }

    public void setuser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Boolean getUser_valid() {
        return user_valid;
    }

    public void setUser_valid(Boolean user_valid) {
        this.user_valid = user_valid;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public Integer getaccount() {
        return account;
    }

    public void setaccount(Integer account) {
        this.account = account;
    }



}
