package com.example.dotdot.dao;

import com.example.dotdot.entity.Message;
import java.util.List;

public interface MessageDao {

    List<Message> getMessages(Integer user_id);

    void changeType(Integer id);
}
