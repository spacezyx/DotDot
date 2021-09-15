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
public class ViewControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getUrlForSimple_search() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getUrlForSimple_search", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }

    @Test
    void getUrlForQa_mrc() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getUrlForQa_mrc", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }

    @Test
    void getUrlForQa_system() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/getUrlForQa_system", requestEntity,  String.class);

        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);
    }
}