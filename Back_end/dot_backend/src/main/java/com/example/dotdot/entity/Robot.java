package com.example.dotdot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "robot")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private int user_id;
    @JSONField(ordinal =2)
    private String name;
    @JSONField(ordinal =3)
    private String APIKey;
    @JSONField(ordinal =4)
    private String APISecret;
    @JSONField(ordinal =5)
    private String last_time;
    @JSONField(ordinal =6)
    private String establish_time;
    @JSONField(ordinal =7)
    private Boolean valid;
    @JSONField(ordinal =8)
    private Integer used_times;
    @JSONField(ordinal =9)
    private Integer type;

    public  Robot(){

    }

    public Robot(Integer id,Integer user_id,String name,String APIKey,String APISecret,
                 String last_time,String establish_time,Boolean valid,Integer used_times,Integer type){
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.APIKey = APIKey;
        this.APISecret = APISecret;
        this.last_time = last_time;
        this.establish_time = establish_time;
        this.valid = valid;
        this.used_times = used_times;
        this.type = type;
    }

}
