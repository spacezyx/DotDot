package com.example.dotdot.controller;

import com.example.dotdot.entity.Robot;
import com.example.dotdot.utils.apputils.APPUtil;
import com.example.dotdot.service.RobotService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RobotController {

    @Autowired
    private RobotService robotService;

    /**
     * 通过user_id获取所有的机器人
     * @param params   "user_id" int
     * @return List<Robot>
     * Robot: Integer id,Integer user_id,String name,String apikey,
     *                  String last_time,String establish_time,Boolean valid,Integer used_times
     */
    @RequestMapping("/getRobots")
    public List<Robot> getRobots(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        return robotService.getRobots(user_id);
    }

    /**
     * 修改该机器人信息
     * Robot: Integer id,Integer user_id,String name,String apikey,
     *                  String last_time,String establish_time,Boolean valid,Integer used_times
     * 目前可改name valid last_time
     * @param params  "id" int   "name" string    "last_time" string    "valid" boolean
     * @return boolean true
     */
    @RequestMapping("/modifyRobot")
    public boolean modifyRobot(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String last_time = jsonObject.getString("last_time");
        Boolean valid = jsonObject.getBoolean("valid");
        robotService.modifyRobot(id, name, valid,last_time);
        return true;
    }

    /**
     * 更新机器人的APISecret
     * Robot: Integer id,Integer user_id,String name,String apikey,
     *                  String last_time,String establish_time,Boolean valid,Integer used_times
     * @param params  "id" int
     * @return boolean true
     */
    @RequestMapping("/updateAPISecret")
    public String updateAPISecret(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer id = jsonObject.getInt("id");

        String apikey = jsonObject.getString("apikey");
        String apisecret = APPUtil.getAPISecret(apikey);
        robotService.updateAPISecret(id,apisecret);
        return apisecret;
    }


    /**
     * 新增机器人
     * 加机器人之前会先看看这个用户是否存在未激活机器人 如果存在则返回false
     * 只有没有机器人或已有机器人全部激活才能新增机器人 并返回true
     * Robot: Integer id,Integer user_id,String name,String apikey,
     *                  String last_time,String establish_time,Boolean valid,Integer used_times
     * @param params  "user_id" int    "name" string    "last_time" string    "establish_time" string    "valid" boolean    "used_times" int "type"  int
     * @return boolean true/false
     */
    //加机器人之前会先看看这个用户是否存在未激活机器人 如果存在则返回false 只有没有机器人或已有机器人全部激活才能新增机器人
    @RequestMapping("/addRobot")
    public boolean addRobot(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        String name = jsonObject.getString("name");
        String apiKey = APPUtil.getAPIKey();
        String last_time = jsonObject.getString("last_time");
        String establish_time = jsonObject.getString("establish_time");
        Boolean valid = jsonObject.getBoolean("valid");
        Integer used_times = jsonObject.getInt("used_times");
        Integer type = jsonObject.getInt("type");
        List<Robot> invalidRobots = robotService.getInvalidRobots(user_id);
        System.out.println(invalidRobots);
        if(invalidRobots.isEmpty()){
            robotService.addRobot(user_id,name,apiKey,last_time,establish_time,valid,used_times,type);
            return true;
        }
        else
            return false;
    }

    /**
     * 购买机器人次数
     * 传入用户的id  robot的apikey 以及 购买金额
     * @param params  "user_id" int    "apikey" string    "money" int
     * @return boolean true/false
     */
    @RequestMapping("/buyTimes")
    public boolean buyTimes(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        String apikey = jsonObject.getString("apikey");
        Integer money = jsonObject.getInt("money");
        Integer times = jsonObject.getInt("times");
        return robotService.buyTimes(user_id,apikey,money,times);
    }

    /**
     * 重置
     * 传入用户的id  删除这个id下所有的robot
     * @param params  "user_id" int
     * @return void
     */
    @RequestMapping("/Reset")
    public void Reset(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        robotService.Reset(user_id);
    }

}
