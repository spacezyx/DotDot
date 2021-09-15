package com.example.dotdot.repository;

import com.example.dotdot.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RegisterRepository extends JpaRepository<Register,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into dotdot.register (username,check_code,timestamp) values(?1, ?2,?3)", nativeQuery = true)
    void addRegister( @Param("username") String username, @Param("check_code") String check_code,@Param("timestamp") String timestamp);


    @Query(value = "from Register where username = :username and check_code = :check_code ")
    Register checkRegister(@Param("username") String username, @Param("check_code") String check_code);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Register WHERE username = :username")
    void deleteRegister(@Param("username") String username);
}
