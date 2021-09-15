package com.example.dotdot.controller;

import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Feedback;
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
public class FeedbackControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetFeedbacks(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getFeedbacks", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);

        Feedback f1 = new Feedback(1,1,"用户反馈可以返回处理结果","2021-04-08 11:48:24.933164",false);
        Feedback f2 = new Feedback(2,1,"用户帮助可以明晰一些","2021-04-11 11:48:24.933164",false);

        List<Feedback> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        String us =JSON.toJSONString(u);
        System.out.println("u is "+ u);
        Assert.assertEquals(result,us);
    }

    @Test
    public void testaddFeedback(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", 2);
        paramMap.put("content","content1");
        paramMap.put("timestamp","20200728");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/addFeedback", requestEntity,  String.class);
        Assert.assertEquals(result,"true");
        Assert.assertNotEquals(result,"false");
    }

    @Test
    public void testchangeFeedbackType(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 1);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/changeFeedbackType", requestEntity,  String.class);
        Assert.assertEquals(result,"true");
        Assert.assertNotEquals(result,"false");
    }

}
