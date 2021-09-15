package com.example.dotdot.dao;

import com.example.dotdot.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao {

    User checkUserByUsername(String username, String password);

    User checkUserByEmail(String email, String password);

    List<User> getUsers();

    void Register(String username, String password, Integer user_type,String email);

    void modifyImg(Integer id,String image);

    User alreadyHasName(String username);

    User alreadyHasEmail(String email);

    User modifyPassword(String username,String password);

    Integer getUserIdByUsername(String username);
}
