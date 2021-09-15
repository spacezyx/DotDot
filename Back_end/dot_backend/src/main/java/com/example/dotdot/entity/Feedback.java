package com.example.dotdot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "feedback")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private Integer user_id;
    @JSONField(ordinal =2)
    private String content;
    @JSONField(ordinal =3)
    private String timestamp;
    @JSONField(ordinal =4)
    private Boolean type;

    public Feedback(){

    }

    public Feedback(int id,Integer user_id,String content,String timestamp,Boolean type){
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;

    }

}
