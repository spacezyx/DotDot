package com.example.dotdot.controller;

import com.example.dotdot.entity.Message;
import com.example.dotdot.service.MessageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 通过user_id获取所有的消息
     * @param params "user_id" int
     * @return List<Message>
     * Message: Integer id,Integer user_id,String title,String content,String timestamp,Boolean type
     */
    @RequestMapping("/getMessages")
    public List<Message> getMessages(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        return  messageService.getMessages(user_id);
    }

    /**
     * 改变消息的type（目前只能true变false/false变false）
     * 注意传入的内容是消息的id
     * Message: Integer id,Integer user_id,String title,String content,String timestamp,Boolean type
     * @param params  "id" int
     * @return boolean true
     */
    @RequestMapping("/changeMessageType")
    public Boolean changeType(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer id = jsonObject.getInt("id");
        messageService.changeType(id);
        return  true;
    }
}
