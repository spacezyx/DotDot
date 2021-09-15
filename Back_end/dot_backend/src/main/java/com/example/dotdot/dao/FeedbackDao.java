package com.example.dotdot.dao;

import com.example.dotdot.entity.Feedback;
import java.util.List;

public interface FeedbackDao {

    void addFeedback(Integer user_id,String content,String timestamp);

    void deleteOne(Integer id);

    List<Feedback> getFeedbacks(Integer user_id);

    void changeType(Integer id);
}
