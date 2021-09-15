package com.example.dotdot.daoimpl;

import com.example.dotdot.entity.User;
import com.example.dotdot.dao.UserDao;
import com.example.dotdot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUserByUsername(String username, String password){

        return userRepository.checkUserByUsername(username,password);
    }

    @Override
    public User checkUserByEmail(String email, String password){
        return userRepository.checkUserByEmail(email,password);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public void Register(String username, String password, Integer user_type,String email) {
        userRepository.userRegister(username,password,user_type,email);
    }

    @Override
    public void modifyImg(Integer id,String image){
        System.out.println("hhhhhh");
        userRepository.modifyImg(id,image);
    }

    @Override
    public User alreadyHasName(String username){
        return userRepository.alreadyHasName(username);
    }

    @Override
    public User alreadyHasEmail(String email){
        return userRepository.alreadyHasEmail(email);
    }

    @Override
    public User modifyPassword(String username,String password){
        userRepository.modifyPassword(username,password);
        return userRepository.alreadyHasName(username);
    }

    @Override
    public Integer getUserIdByUsername(String username){
        return userRepository.getUserIdByUsername(username);
    }
}
