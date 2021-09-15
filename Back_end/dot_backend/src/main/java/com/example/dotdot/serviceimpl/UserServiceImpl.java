package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.UserDao;
import com.example.dotdot.entity.User;
import com.example.dotdot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void Register(String username, String password, Integer user_type,String email){
        userDao.Register(username,password,user_type,email);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User checkUserByUsername(String username, String password){
        return userDao.checkUserByUsername(username,password);
    }
    @Override
    public User checkUserByEmail(String email, String password){
        return userDao.checkUserByEmail(email,password);
    }



    @Override
    public void modifyImg(Integer id,String image){
        userDao.modifyImg(id,image);
    }

    @Override
    public User modifyPassword(String username,String password){
        return userDao.modifyPassword(username,password);
    }

    @Override
    public User alreadyHasName(String username){
        return userDao.alreadyHasName(username);
    }

    @Override
    public User alreadyHasEmail(String email){
        return userDao.alreadyHasEmail(email);
    }

    @Override
    public Integer getUserIdByUsername(String username){
        return userDao.getUserIdByUsername(username);
    }
}
