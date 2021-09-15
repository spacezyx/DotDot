package com.example.dotdot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "message")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private int user_id;
    @JSONField(ordinal =2)
    private String title;
    @JSONField(ordinal =3)
    private String content;
    @JSONField(ordinal =4)
    private String timestamp;
    @JSONField(ordinal =5)
    private Boolean type;

    public Message(){

    }

    public Message(Integer id,Integer user_id,String title,String content,String timestamp,Boolean type){
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
    }
}
