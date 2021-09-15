package com.example.dotdot.dao;

import com.example.dotdot.entity.Robot;

import java.util.List;

public interface RobotDao {

    String addRobot(Integer user_id,String name,String APIKey,String last_time,
                  String establish_time,Boolean valid,Integer used_times,Integer type);

    void deleteOne(Integer id);

    List<Robot> getRobots(Integer user_id);

    List<Robot> getInvalidRobots(Integer user_id);

    void modifyRobot(Integer id, String name,Boolean valid,String last_time);

    void updateAPISecret(Integer id, String APISecret);

    String getAPISecretByApikey(String APIKey);

    Boolean getValidByApikey(String APIKey);

    Integer getTypeByApikey(String APIKey);

    Integer getTimesByApikey(String APIKey);

    void decreaseTimesByApikey(String APIKey);

    void UpdateLastTimeByApikey(String APIKey,String last_time);

    boolean buyTimes(Integer user_id,String apikey,Integer money,Integer times);

    void Reset(Integer user_id);
}
