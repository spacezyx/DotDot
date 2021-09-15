package com.example.dotdot.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RobotControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void getRobots() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getRobots", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);

    }

    @Test
    void modifyRobot() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 3);
        paramMap.put("name", "2");
        paramMap.put("last_time", "2");
        paramMap.put("valid", true);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/modifyRobot", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }

    @Test
    void updateAPISecret() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 2);
        paramMap.put("apikey", "2");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/updateAPISecret", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }

    @Test
    void addRobot() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", 2);
        paramMap.put("name", "测试机3号");
        paramMap.put("last_time", "2");
        paramMap.put("establish_time", "2");
        paramMap.put("valid", false);
        paramMap.put("used_times", 20);
        paramMap.put("type", 1);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/addRobot", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("user_id", 2);
        paramMap1.put("name", "测试机4号");
        paramMap1.put("last_time", "2");
        paramMap1.put("establish_time", "2");
        paramMap1.put("valid", false);
        paramMap1.put("used_times", 20);
        paramMap1.put("type", 1);

        HttpEntity<Map<String, Object>> requestEntity1 = new HttpEntity<>(paramMap1, requestHeaders);
        String result1 = restTemplate.postForObject("/addRobot", requestEntity1,  String.class);

        Assert.assertEquals(result1.isEmpty(),false);
        Assert.assertNotEquals(result1.isEmpty(),true);
        System.out.println(result);
    }
}