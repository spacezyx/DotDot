package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.FeedbackDao;
import com.example.dotdot.entity.Feedback;
import com.example.dotdot.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> getFeedbacks(Integer user_id) {
        return feedbackDao.getFeedbacks(user_id);
    }

    @Override
    public void deleteOne(Integer id){
        feedbackDao.deleteOne(id);
    }

    @Override
    public void addFeedback(Integer user_id, String content,String timestamp) {
        feedbackDao.addFeedback(user_id,content,timestamp);
    }
    @Override
    public void changeType(Integer id){
       feedbackDao.changeType(id);
    }

}
