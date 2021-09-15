package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Register;
import com.example.dotdot.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest( MailController.class)
public class MailControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private RegisterService registerService;

    @Test
    public void sendMail() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zz");
        paramMap.put("email","1292171824@qq.com");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/sendMail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void checkCode() throws Exception{

        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zz");
        paramMap.put("checkCode","190681");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("username", "zz");
        paramMap1.put("checkCode","146592");
        String jsonStr1=JSON.toJSONString(paramMap1);

        Map<String, Object> paramMap2= new HashMap();
        paramMap2.put("username", "zz");
        paramMap2.put("checkCode","92");
        String jsonStr2=JSON.toJSONString(paramMap2);

        Register re = new Register(1,"zz","92","20201597");

        given(this.registerService.checkRegister("zz","92")).willReturn(re);


        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/checkCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/checkCode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr2)
                )
                .andExpect(status().isOk());
    }

}
