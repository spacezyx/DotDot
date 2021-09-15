package com.example.dotdot.serviceimpl;

import com.example.dotdot.dao.RobotDao;
import com.example.dotdot.entity.Robot;
import com.example.dotdot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RobotServiceImpl implements RobotService {

    @Autowired
    private RobotDao robotDao;

    @Override
    public List<Robot> getRobots(Integer user_id) {
        return robotDao.getRobots(user_id);
    }

    @Override
    public List<Robot> getInvalidRobots(Integer user_id) {
        return robotDao.getInvalidRobots(user_id);
    }

    @Override
    public void deleteOne(Integer id){
        robotDao.deleteOne(id);
    }

    @Override
    public void  addRobot(Integer user_id,String name,String APIKey,String last_time,
                          String establish_time,Boolean valid,Integer used_times,Integer type) {
        robotDao.addRobot(user_id,name,APIKey,last_time,establish_time,valid,used_times,type);
    }

    @Override
    public void modifyRobot(Integer id, String name,Boolean valid,String last_time){
        robotDao.modifyRobot(id,name,valid,last_time);
    }

    @Override
    public void updateAPISecret(Integer id, String APISecret){
        robotDao.updateAPISecret(id,APISecret);
    }

    @Override
    public String getAPISecretByApikey(String APIKey){
        return robotDao.getAPISecretByApikey(APIKey);
    }

    @Override
    public Boolean getValidByApikey(String APIKey){
        return robotDao.getValidByApikey(APIKey);
    }

    @Override
    public Integer getTimesByApikey(String APIKey){
        return robotDao.getTimesByApikey(APIKey);
    }

    @Override
    public void decreaseTimesByApikey(String APIKey){
        robotDao.decreaseTimesByApikey(APIKey);
    }

    @Override
    public void UpdateLastTimeByApikey(String APIKey,String last_time){
        robotDao.UpdateLastTimeByApikey(APIKey,last_time);
    }

    @Override
    public Integer getTypeByApikey(String APIKey){
        return robotDao.getTypeByApikey(APIKey);
    }

    @Override
    public boolean buyTimes(Integer user_id,String apikey,Integer money,Integer times){
        return  robotDao.buyTimes(user_id,apikey,money,times);
    }

    @Override
    public  void Reset(Integer user_id){
        robotDao.Reset(user_id);
    }
}
