package com.example.dotdot.service;

import com.example.dotdot.entity.Message;

import java.util.List;


public interface MessageService {

    List<Message> getMessages(Integer user_id);

    void changeType(Integer id);
}
