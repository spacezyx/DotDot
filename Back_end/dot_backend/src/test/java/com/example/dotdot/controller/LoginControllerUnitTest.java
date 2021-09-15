package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.constant.Constant;
import com.example.dotdot.entity.User;
import com.example.dotdot.service.UserService;
import com.example.dotdot.utils.msgutils.Msg;
import com.example.dotdot.utils.msgutils.MsgUtil;
import net.sf.json.JSONObject;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LoginController.class)
public class LoginControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void Login() throws Exception{

        User user = new User(1,"h","2","3",1,true,"1",2000);
        User user1 = new User(1,"h","2","3",1,false,"1",2000);

        JSONObject data = JSONObject.fromObject(user);
        data.remove(Constant.PASSWORD);
        given(this.userService.checkUserByUsername("1","3")).willReturn(user);
        given(this.userService.checkUserByEmail("2","3")).willReturn(user1);

        Msg msg = MsgUtil.makeMsg(0,"欢迎登录点宝系统！",data);
        String t = msg.toString();


        Map<String, Object> paramMap= new HashMap();
        paramMap.put("username", "1");
        paramMap.put("password", "3");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("email", "2");
        paramMap1.put("password", "3");
        String jsonStr1=JSON.toJSONString(paramMap1);
        System.out.println(jsonStr1);

        Map<String, Object> paramMap2= new HashMap();
        paramMap2.put("email", "0");
        paramMap2.put("password", "3");
        String jsonStr2=JSON.toJSONString(paramMap2);
        System.out.println(jsonStr2);


        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonStr)
                )
                .andExpect(status().isOk()).andExpect(content().string(t));

        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonStr1)
                )
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/login")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(jsonStr2)
                )
                .andExpect(status().isOk());
    }

}
