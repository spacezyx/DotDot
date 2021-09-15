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
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void register() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zengshuai");
        paramMap.put("password", "ExHyw8XR");
        paramMap.put("user_type", "1");
        paramMap.put("email", "111111@qq.com");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/Register", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }

    @Test
    void alreadyHasName() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "handsome");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/alreadyHasName", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("username", "z");
        HttpEntity<Map<String, Object>> requestEntity1 = new HttpEntity<>(paramMap1, requestHeaders);
        String result1 = restTemplate.postForObject("/alreadyHasName", requestEntity1,  String.class);

        Assert.assertEquals(result1.isEmpty(),false);
        Assert.assertNotEquals(result1.isEmpty(),true);
        System.out.println(result1);


    }

    @Test
    void alreadyHasEmail() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("email", "1292171824@qq.com");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/alreadyHasEmail", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("email", "z");
        HttpEntity<Map<String, Object>> requestEntity1 = new HttpEntity<>(paramMap1, requestHeaders);
        String result1 = restTemplate.postForObject("/alreadyHasEmail", requestEntity1,  String.class);

        Assert.assertEquals(result1.isEmpty(),false);
        Assert.assertNotEquals(result1.isEmpty(),true);
        System.out.println(result1);
    }

    @Test
    void modifyImg() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", "2");
        paramMap.put("image", "tmpimage");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/modifyImg", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);

    }

    @Test
    void modifypassword() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "ugly");
        paramMap.put("password", "tmpimage");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/modifypassword", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
    }
}