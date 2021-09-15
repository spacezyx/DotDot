package com.example.dotdot.daoimpl;

import com.example.dotdot.dao.RegisterDao;
import com.example.dotdot.entity.Register;
import com.example.dotdot.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDaoImpl implements RegisterDao {

    @Autowired
    RegisterRepository registerRepository;

    @Override
    public Register checkRegister(String username, String password){
        return registerRepository.checkRegister(username,password);
    }

    @Override
    public void addRegister(String username,String check_code,String timestamp){
        registerRepository.addRegister(username,check_code,timestamp);
    }

    @Override
    public void deleteRegister(String username){
        registerRepository.deleteRegister(username);
    }

}
