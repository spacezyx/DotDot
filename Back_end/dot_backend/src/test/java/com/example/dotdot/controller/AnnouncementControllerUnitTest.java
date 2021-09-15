package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Announcement;
import com.example.dotdot.service.AnnouncementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AnnouncementController.class)
public class AnnouncementControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnnouncementService announcementService;

    @Test
    public void getAnnouncements() throws Exception{
        Announcement f1 = new Announcement(1,"点宝今天成立啦~","点宝Dotdot于2021年7月成立","2021-07-08 11:48:24.933164");
        Announcement f2 = new Announcement(2,"第一次测试公告","点宝Dotdot将于7月18日开始测试","2021-07-18 11:48:24.933164");

        List<Announcement> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);
        String us = JSON.toJSONString(u);
        System.out.println("u is "+ u);


        given(this.announcementService.getAnnouncements()).willReturn(u);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getAnnouncements")
                )
                .andExpect(status().isOk()).andExpect(content().string(us));
    }

}
