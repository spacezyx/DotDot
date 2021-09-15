package com.example.dotdot.controller;

import com.example.dotdot.entity.Feedback;
import com.example.dotdot.service.FeedbackService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 新增反馈
     * Feedback: int id,Integer user_id,String content,String timestamp
     * @param params "user_id" int    "content" string    "timestamp" string
     * @return boolean true
     */
    @RequestMapping("/addFeedback")
    public boolean addFeedback(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        String content = jsonObject.getString("content");
        String timestamp = jsonObject.getString("timestamp");
        feedbackService.addFeedback(user_id,content,timestamp);
        return true;
    }

    /**
     * 通过user_id获取所有的反馈
     * @param params "user_id" int
     * @return List<Feedback>
     * Feedback: int id,Integer user_id,String content,String timestamp
     */
    @RequestMapping("/getFeedbacks")
    public List<Feedback> getFeedbacks(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        return feedbackService.getFeedbacks(user_id);
    }

    /**
     * 改变反馈的type（目前只能true变false/false变false）
     * 注意传入的内容是反馈的id
     * @param params  "id" int
     * @return boolean true
     */
    @RequestMapping("/changeFeedbackType")
    public Boolean changeType(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer id = jsonObject.getInt("id");
        feedbackService.changeType(id);
        return  true;
    }
}
