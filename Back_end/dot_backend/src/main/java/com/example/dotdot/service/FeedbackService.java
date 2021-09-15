package com.example.dotdot.service;

import com.example.dotdot.entity.Feedback;

import java.util.List;


public interface FeedbackService {

    List<Feedback> getFeedbacks(Integer user_id);

    void deleteOne(Integer id);

    void addFeedback(Integer user_id,String content,String timestamp);

    void changeType(Integer id);


}
