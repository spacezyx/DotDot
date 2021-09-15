package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Feedback;
import com.example.dotdot.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FeedbackController.class)
public class FeedbackControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FeedbackService feedbackService;

    @Test
    public void testgetFeedbacks() throws Exception{
        Feedback f1 = new Feedback(1,1,"我觉得zengyuxin很帅","2021-04-08 11:48:24.933164",true);
        Feedback f2 = new Feedback(2,1,"我也觉得","2021-04-11 11:48:24.933164",false);
        List<Feedback> u = new ArrayList<>();
        u.add(f1);
        u.add(f2);

        System.out.println("u is "+ u);

        String us = JSON.toJSONString(u);
        System.out.println("us is "+ us);


        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");

        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        given(this.feedbackService.getFeedbacks(1)).willReturn(u);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getFeedbacks")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStr)
                )
                .andExpect(status().isOk()).andExpect(content().string(us));
    }

    @Test
    public void testaddFeedback() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", 2);
        paramMap.put("content","content1");
        paramMap.put("timestamp","20200728");

        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/addFeedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());


    }

    @Test
    public void changeFeedbackType() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 1);
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/changeFeedbackType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }


}
