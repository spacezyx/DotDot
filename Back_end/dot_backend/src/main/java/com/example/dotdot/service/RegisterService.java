package com.example.dotdot.service;

import com.example.dotdot.entity.Register;


public interface RegisterService {
    Register checkRegister(String username, String password);

    void addRegister(String username,String check_code,String timestamp);

    void deleteRegister(String username);

}
