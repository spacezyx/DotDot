package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Message;
import com.example.dotdot.service.MessageService;
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


@WebMvcTest(MessageController.class)
public class MessageControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void testgetFeedbacks() throws Exception{
        Message f1 = new Message(1,1,"机器人创建成功","测试机1号机器人创建成功","2021-04-08 11:48:24.933164",false);

        List<Message> u = new ArrayList<>();
        u.add(f1);
        String us =JSON.toJSONString(u);
        System.out.println("u is "+ u);

        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");

        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        given(this.messageService.getMessages(1)).willReturn(u);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getMessages")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStr)
                )
                .andExpect(status().isOk()).andExpect(content().string(us));
    }



    @Test
    public void changeMessageType() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 1);
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/changeMessageType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }


}
