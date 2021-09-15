package com.example.dotdot.daoimpl;

import com.example.dotdot.dao.RobotDao;
import com.example.dotdot.repository.UserRepository;
import com.example.dotdot.utils.apputils.APPUtil;
import com.example.dotdot.entity.Robot;
import com.example.dotdot.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RobotDaoImpl implements RobotDao {

    @Autowired
    private RobotRepository robotRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Robot> getRobots(Integer user_id) {
        return robotRepository.getRobots(user_id);
    }

    @Override
    public List<Robot> getInvalidRobots(Integer user_id) {
        return robotRepository.getInvalidRobots(user_id);
    }

    @Override
    public void deleteOne(Integer id){
        robotRepository.deleteOne(id);
    }

    @Override
    public String addRobot(Integer user_id,String name,String APIKey,String last_time,
                              String establish_time,Boolean valid,Integer used_times,Integer type){
        String APISecret = APPUtil.getAPISecret(APIKey);
        robotRepository.addRobot(user_id,name,APIKey,last_time,establish_time,valid,used_times,APISecret,type);
        return APISecret;
    }

    @Override
    public void modifyRobot(Integer id, String name,Boolean valid,String last_time){
        robotRepository.modifyRobot(id,name,valid,last_time);
    }

    @Override
    public void updateAPISecret(Integer id, String APISecret){
        robotRepository.updateAPISecret(id,APISecret);
    }

    @Override
    public String getAPISecretByApikey(String APIKey){
        return robotRepository.getAPISecretByApikey(APIKey);
    }

    @Override
    public Integer getTypeByApikey(String APIKey){
        return robotRepository.getTypeByApikey(APIKey);
    }

    @Override
    public Boolean getValidByApikey(String APIKey){
        return robotRepository.getValidByApikey(APIKey);
    }

    @Override
    public Integer getTimesByApikey(String APIKey){
        return robotRepository.getTimesByApikey(APIKey);
    }

    @Override
    public void decreaseTimesByApikey(String APIKey){
        robotRepository.decreaseTimesByApikey(APIKey);
    }

    @Override
    public void UpdateLastTimeByApikey(String APIKey,String last_time){
        robotRepository.UpdateLastTimeByApikey(APIKey,last_time);
    }

    @Override
    public boolean buyTimes(Integer user_id,String apikey,Integer money,Integer times){
        Integer account = userRepository.getAccountById(user_id);
        if(money > account)
            return false;
        else {
            userRepository.decreaseAccountById(user_id,money);
            robotRepository.increaseTimesByApikey(apikey,times);
            return true;
        }
    }

    @Override
    public void Reset(Integer user_id){
        System.out.println(user_id);
        robotRepository.Reset(user_id);
    }
}
