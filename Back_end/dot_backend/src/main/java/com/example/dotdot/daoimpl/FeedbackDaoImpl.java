package com.example.dotdot.daoimpl;

import com.example.dotdot.dao.FeedbackDao;
import com.example.dotdot.entity.Feedback;
import com.example.dotdot.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getFeedbacks(Integer user_id) {
        return feedbackRepository.getFeedbacks(user_id);
    }

    @Override
    public void deleteOne(Integer id){
        feedbackRepository.deleteOne(id);
    }

    @Override
    public void addFeedback(Integer user_id,String content,String timestamp){
        feedbackRepository.addFeedback(user_id,content,timestamp);
    }

    @Override
    public void changeType(Integer id){
        feedbackRepository.changeType(id);
    }
}
