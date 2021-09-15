package com.example.dotdot.service;

import com.example.dotdot.entity.Robot;

import java.util.List;


public interface RobotService {

    List<Robot> getRobots(Integer user_id);

    void deleteOne(Integer id);

    void addRobot(Integer user_id,String name,String APIKey,String last_time,
                  String establish_time,Boolean valid,Integer used_times,Integer type);

    void modifyRobot(Integer id, String name,Boolean valid,String last_time);

    List<Robot> getInvalidRobots(Integer user_id);

    void updateAPISecret(Integer id, String APISecret);

    String getAPISecretByApikey(String APIKey);

    Boolean getValidByApikey(String APIKey);

    Integer getTimesByApikey(String APIKey);

    void decreaseTimesByApikey(String APIKey);

    Integer getTypeByApikey(String APIKey);

    void UpdateLastTimeByApikey(String APIKey,String last_time);

    boolean buyTimes(Integer user_id,String apikey,Integer money,Integer times);

    void Reset(Integer user_id);
}
