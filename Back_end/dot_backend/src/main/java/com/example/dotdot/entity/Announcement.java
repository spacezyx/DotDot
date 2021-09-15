package com.example.dotdot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "announcement")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(ordinal =0)
    private int id;
    @JSONField(ordinal =1)
    private String title;
    @JSONField(ordinal =2)
    private String content;
    @JSONField(ordinal =3)
    private String timestamp;

    public Announcement(){

    }

    public Announcement(int id,String title,String content,String timestamp){
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;

    }
}
