package com.example.dotdot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "register")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private String username;
    @JSONField(ordinal =2)
    private String check_code;
    @JSONField(ordinal =3)
    private String timestamp;

    public Register(){

    }

    public Register(Integer id, String username,String check_code,String timestamp){
        this.id = id;
        this.username = username;
        this.check_code = check_code;
        this.timestamp = timestamp;
    }

}
