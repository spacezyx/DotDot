package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.RegisterDao;
import com.example.dotdot.entity.Register;
import com.example.dotdot.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterDao registerDao;

    @Override
    public Register checkRegister(String username, String password){
        return registerDao.checkRegister(username,password);
    }


    @Override
    public void addRegister(String username,String check_code,String timestamp){
        registerDao.addRegister(username,check_code,timestamp);
    }

    @Override
    public void deleteRegister(String username){
        registerDao.deleteRegister(username);
    }
}
