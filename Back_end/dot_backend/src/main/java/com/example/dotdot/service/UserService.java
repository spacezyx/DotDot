package com.example.dotdot.service;

import com.example.dotdot.entity.User;

import java.util.List;


public interface UserService {
    void Register(String username, String password, Integer user_type,String email);

    List<User> getUsers();

    User checkUserByUsername(String username, String password);

    User checkUserByEmail(String email, String password);

    void modifyImg(Integer id,String image);

    User modifyPassword(String username,String password);

    User alreadyHasName(String username);

    User alreadyHasEmail(String email);

    Integer getUserIdByUsername(String username);

}
