package com.example.dotdot.controller;

import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Announcement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnnouncementControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAnnouncements(){
        String result = restTemplate.getForObject("/getAnnouncements", String.class);
        Assert.assertEquals(result.isEmpty(),false);
        Assert.assertNotEquals(result.isEmpty(),true);
        System.out.println(result);

        Announcement f1 = new Announcement(1,"点宝今天成立啦~","点宝Dotdot于2021年7月成立","2021-07-08 11:48:24.933164");
        Announcement f2 = new Announcement(2,"第一次测试公告","点宝Dotdot将于7月18日开始测试","2021-07-18 11:48:24.933164");

        List<Announcement> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        String us = JSON.toJSONString(u);
        System.out.println("u is "+ u);
        Assert.assertEquals(result,us);

    }


}
