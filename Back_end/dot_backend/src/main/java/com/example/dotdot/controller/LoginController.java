package com.example.dotdot.controller;

import com.example.dotdot.constant.Constant;
import com.example.dotdot.entity.User;
import com.example.dotdot.service.UserService;
import com.example.dotdot.utils.msgutils.Msg;
import com.example.dotdot.utils.msgutils.MsgCode;
import com.example.dotdot.utils.msgutils.MsgUtil;
import com.example.dotdot.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录 支持邮箱登录或用户名登录
     * @param params  "username" string    "password" string    "email" string
     * @return Msg
     * Msg内容较多不便展示请移步代码com.example.dotdot.utils.msgutils.MsgUtil
     */
    @RequestMapping("/login")
    public Msg login(@RequestBody Map<String, String> params){
        String username = params.get(Constant.USERNAME);
        String password = params.get(Constant.PASSWORD);
        String email = params.get(Constant.EMAIL);
        User auth = userService.checkUserByUsername(username, password);
        if(auth == null){
            auth = userService.checkUserByEmail(email,password);
        }
        if(auth != null){
            JSONObject obj = new JSONObject();
            obj.put(Constant.USER_ID, auth.getid());
            obj.put(Constant.USERNAME, auth.getUsername());
            obj.put(Constant.USER_TYPE, auth.getuser_type());
            SessionUtil.setSession(obj);

            JSONObject data = JSONObject.fromObject(auth);
            data.remove(Constant.PASSWORD);
            if(auth.getUser_valid())
                return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
            else{
                return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
            }
        }
        else{
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }
}
