package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.MessageDao;
import com.example.dotdot.entity.Message;
import com.example.dotdot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> getMessages(Integer user_id) {
        return messageDao.getMessages(user_id);
    }

    @Override
    public void changeType(Integer id){
        messageDao.changeType(id);
    }
}
