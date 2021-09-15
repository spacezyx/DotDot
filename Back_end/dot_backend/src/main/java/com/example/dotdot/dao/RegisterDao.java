package com.example.dotdot.dao;

import com.example.dotdot.entity.Register;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao {

    Register checkRegister(String username, String password);

    void addRegister(String username,String check_code,String timestamp);

    void deleteRegister(String username);

}
