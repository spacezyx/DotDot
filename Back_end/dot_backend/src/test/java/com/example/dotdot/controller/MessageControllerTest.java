package com.example.dotdot.controller;

import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Message;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetMessages(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getMessages", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);

        Message f1 = new Message(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164",false);

        List<Message> u = new ArrayList<>();
        u.add(f1);
        String us =JSON.toJSONString(u);
        System.out.println("u is "+ u);
        Assert.assertEquals(result,us);
    }


    @Test
    public void testchangeFeedbackType(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 1);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/changeMessageType", requestEntity,  String.class);
        Assert.assertEquals(result,"true");
        Assert.assertNotEquals(result,"false");
    }

}
