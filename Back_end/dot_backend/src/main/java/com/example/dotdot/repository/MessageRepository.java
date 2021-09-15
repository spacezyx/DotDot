package com.example.dotdot.repository;

import com.example.dotdot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    @Query(value = "from Message where user_id = :user_id")
    List<Message> getMessages(@Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Message SET type = false WHERE id = :id")
    void changeType(@Param("id") Integer id);
}
