package com.example.dotdot.controller;

import com.example.dotdot.entity.User;
import com.example.dotdot.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * User: int id, String username, String password,String email, Integer user_type, boolean user_valid,String image
     * @param params  "username" string    "password" string    "user_type" Int    "email" string
     * @return boolean true
     */
    @RequestMapping("/Register")
    public boolean Register(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Integer user_type = jsonObject.getInt("user_type");
        String email = jsonObject.getString("email");
        userService.Register(username,password,user_type,email);
        return true;
    }

    /**
     * 检测重名
     * User: int id, String username, String password,String email, Integer user_type, boolean user_valid,String image
     * 存在重名为true 不存在重名为false
     * @param params  "username" string
     * @return boolean true/false
     */
    @RequestMapping("/alreadyHasName")
    public boolean alreadyHasName(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String username = jsonObject.getString("username");
        User li = userService.alreadyHasName(username);
        if(li == null)
            return false;
        else
            return true;
    }

    /**
     * 检测邮箱重复
     * User: int id, String username, String password,String email, Integer user_type, boolean user_valid,String image
     * 邮箱已存在为true  邮箱不存在为false
     * @param params  "email" string
     * @return boolean true/false
     */
    @RequestMapping("/alreadyHasEmail")
    public boolean alreadyHasEmail(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String email = jsonObject.getString("email");
        User li = userService.alreadyHasEmail(email);
        if(li == null)
            return false;
        else
            return true;
    }

    /**
     * 修改用户头像 格式为base64
     * User: int id, String username, String password,String email, Integer user_type, boolean user_valid,String image
     * @param params  "id" int    "image" string
     * @return boolean true
     */
    @RequestMapping("/modifyImg")
    public boolean  modifyImg(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer id = jsonObject.getInt("id");
        String image = jsonObject.getString("image");
        userService.modifyImg(id,image);
        return true;
    }


    @RequestMapping("/modifypassword")
    public User  modifypassword(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        return userService.modifyPassword(username,password);
    }

    /**
     * 返回所有用户 前端应该还没用到
     * @return List<User>
     * User: int id, String username, String password,String email, Integer user_type, boolean user_valid,String image
     */
    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

//    @RequestMapping("/ValidUser")
//    public void ValidUser(@RequestParam("username") String username, @RequestParam("user_valid") Boolean user_vaild){
//        userService.changeValid(username,user_vaild);
//    }

}
