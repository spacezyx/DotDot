package com.example.dotdot.controller;


import com.alibaba.fastjson.JSON;
import com.example.dotdot.entity.Robot;
import com.example.dotdot.service.RobotService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RobotController.class)
public class RobotControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RobotService robotService;

    @Test
    public void getRobots() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", "1");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);
        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/getRobots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void modifyRobot() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 2);
        paramMap.put("name", "2");
        paramMap.put("last_time", "2");
        paramMap.put("valid", true);
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);
        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/modifyRobot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateAPISecret() throws Exception{
        Map<String, Object> paramMap= new HashMap();
        paramMap.put("id", 2);
        paramMap.put("apikey", "2");
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);
        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/updateAPISecret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void addRobot() throws Exception{

        Robot f1 = new Robot(1,1,"w","w","w","w","w",false,1,2);

        List<Robot> u = new ArrayList<>();
        u.add(f1);
        given(this.robotService.getInvalidRobots(2)).willReturn(u);

        Map<String, Object> paramMap= new HashMap();
        paramMap.put("user_id", 2);
        paramMap.put("name", "测试机3号");
        paramMap.put("last_time", "2");
        paramMap.put("establish_time", "2");
        paramMap.put("valid", false);
        paramMap.put("used_times", 20);
        paramMap.put("type", 1);
        String jsonStr=JSON.toJSONString(paramMap);
        System.out.println(jsonStr);

        Map<String, Object> paramMap1= new HashMap();
        paramMap1.put("user_id", 1);
        paramMap1.put("name", "测试机3号");
        paramMap1.put("last_time", "2");
        paramMap1.put("establish_time", "2");
        paramMap1.put("valid", false);
        paramMap1.put("used_times", 20);
        paramMap1.put("type", 1);
        String jsonStr1=JSON.toJSONString(paramMap1);
        System.out.println(jsonStr1);

        // Run test
        mvc.perform(MockMvcRequestBuilders.post("/addRobot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                )
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/addRobot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr1)
                )
                .andExpect(status().isOk());


    }

}
