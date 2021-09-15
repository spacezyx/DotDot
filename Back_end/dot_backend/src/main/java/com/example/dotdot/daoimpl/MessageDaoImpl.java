package com.example.dotdot.daoimpl;

import com.example.dotdot.dao.MessageDao;
import com.example.dotdot.entity.Message;
import com.example.dotdot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getMessages(Integer user_id) {
        return messageRepository.getMessages(user_id);
    }

    @Override
    public void changeType(Integer id){
        messageRepository.changeType(id);
    }
}
