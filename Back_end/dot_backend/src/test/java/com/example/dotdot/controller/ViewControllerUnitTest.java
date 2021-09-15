package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ViewController.class)
public class ViewControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private FeedbackService feedbackService;

    @Test
    public void getUrlForSimple_search() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getUrlForSimple_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void getUrlForQa_mrc() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getUrlForQa_mrc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void getUrlForQa_system() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("question", "上海交通大学被誉为什么？");
        paramMap.put("apikey", "ExHyw8XR");
        paramMap.put("apisecret", "12e3f43bd09aecaacb80d6cd6ca5697ff3eb7a7e");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getUrlForQa_system")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

}
