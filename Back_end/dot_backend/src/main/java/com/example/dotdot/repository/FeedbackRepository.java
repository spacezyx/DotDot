package com.example.dotdot.repository;

import com.example.dotdot.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Query(value = "select * from dotdot.feedback WHERE user_id = :user_id ", nativeQuery = true)
    List<Feedback> getFeedbacks(@Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Feedback WHERE id = :id")
    void deleteOne(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Feedback SET type = false WHERE id = :id")
    void changeType(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into dotdot.feedback (user_id,content,timestamp) values(?1, ?2,?3)", nativeQuery = true)
    void addFeedback( @Param("user_id") Integer user_id, @Param("content") String content,@Param("timestamp") String timestamp);
}
