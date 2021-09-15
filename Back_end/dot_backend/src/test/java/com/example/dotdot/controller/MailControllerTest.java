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
public class MailControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void sendMail() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zz");
        paramMap.put("email","1292171824@qq.com");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/sendMail", requestEntity,  String.class);
        Assert.assertEquals(result,"true");
        Assert.assertNotEquals(result,"false");
    }

    @Test
    void checkCode() {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("username", "zz");
        paramMap1.put("checkCode","659759");
        HttpEntity<Map<String, Object>> requestEntity1 = new HttpEntity<>(paramMap1, requestHeaders);
        String result1 = restTemplate.postForObject("/checkCode", requestEntity1, String.class);
        Assert.assertEquals(result1,"验证码已失效，请重新验证。");

        Map<String, Object> paramMap2= new HashMap();
        paramMap2.put("username", "zz");
        paramMap2.put("checkCode","92");
        HttpEntity<Map<String, Object>> requestEntity2 = new HttpEntity<>(paramMap2, requestHeaders);
        String result2 = restTemplate.postForObject("/checkCode", requestEntity2,  String.class);
        Assert.assertEquals(result2,"验证码错误。");

        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zz");
        paramMap.put("checkCode","977320");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paramMap, requestHeaders);
        String result = restTemplate.postForObject("/checkCode", requestEntity,  String.class);
        Assert.assertEquals(result,"校验通过");

    }
}