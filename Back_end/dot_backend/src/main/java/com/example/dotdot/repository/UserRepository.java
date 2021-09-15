package com.example.dotdot.repository;

import com.example.dotdot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    @Query(value = "select * from dotdot.user", nativeQuery = true)
    List<User> getUsers();

    @Transactional
    @Modifying
    @Query(value = "insert into dotdot.user (username,password,user_type,user_valid,email) values(?1,?2,?3,1,?4)", nativeQuery = true)
    void userRegister(@Param("username") String username,@Param("password") String password, @Param("user_type") Integer user_type,@Param("email") String email);

    @Query(value = "from User where username = :username and password = :password ")
    User checkUserByUsername(@Param("username") String username, @Param("password") String password);

    @Query(value = "from User where email = :email and password = :password ")
    User checkUserByEmail(@Param("email") String email, @Param("password") String password);


    @Query(value = "from User where username = :username")
    User alreadyHasName(@Param("username") String username);

    @Query(value = "from User where email = :email")
    User alreadyHasEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET image = :image WHERE id = :id")
    void modifyImg(@Param("id") Integer id, @Param("image") String image);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET password = :password WHERE username = :username")
    void modifyPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "select id from User WHERE username = :username")
    Integer getUserIdByUsername(String username);

    @Query(value = "select account from User WHERE id = :id")
    Integer getAccountById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE  User SET account = account- :money  WHERE id = :id")
    void decreaseAccountById(@Param("id") Integer id,@Param("money") Integer money);
}
