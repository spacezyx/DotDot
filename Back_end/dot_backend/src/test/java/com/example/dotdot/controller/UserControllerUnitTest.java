package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.User;
import com.example.dotdot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void register() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "zengshuai");
        paramMap.put("password", "ExHyw8XR");
        paramMap.put("user_type", "1");
        paramMap.put("email", "111111@qq.com");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/Register")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void alreadyHasName() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "handsome");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("username", "1");
        String jsonStr1=JSON.toJSONString(paramMap1);
        System.out.println(jsonStr1);


        User user = new User(1,"h","2","3",1,false,"1",2000);

        given(userService.alreadyHasName("1")).willReturn(user);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/alreadyHasName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/alreadyHasName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr1)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void alreadyHasEmail() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("email", "handsome@qq.com");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("email", "1");
        String jsonStr1=JSON.toJSONString(paramMap1);
        System.out.println(jsonStr1);


        User user = new User(1,"h","2","3",1,false,"1",2000);

        given(userService.alreadyHasEmail("1")).willReturn(user);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/alreadyHasEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/alreadyHasEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr1)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void modifyImg() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", "2");
        paramMap.put("image", "tmpimage");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/modifyImg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void modifypassword() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "ugly");
        paramMap.put("password", "tmpimage");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/modifypassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }
}
