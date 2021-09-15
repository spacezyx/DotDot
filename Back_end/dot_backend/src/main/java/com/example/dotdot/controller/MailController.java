package com.example.dotdot.controller;

import com.example.dotdot.entity.Register;
import com.example.dotdot.service.RegisterService;
import com.example.dotdot.utils.timeutils.TimeUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;

@RestController
public class MailController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${mail.fromMail.sender}")
	private String sender;// 发送者

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RegisterService registerService;

    /**
     * 用于发送验证信息
     * @param params  "username" string    "email" string
     * @return string true/false
     */
    //发邮件 传用户名和邮箱进来 然后会发送验证码到邮箱 验证码有效期十分钟 验证验证码调下面的checkCode
    @RequestMapping("/sendMail")
    public Boolean sendMail(@RequestBody Map<String, String> params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String timestamp = TimeUtil.getTime();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
		message.setSubject("点宝Dotdot验证");// 标题
		message.setText(username + ","+"\n"+ "  【点宝DotDot】您的验证码为 "+checkCode+"  验证码有效时长十分钟"+" \n欢迎使用点宝Dotdot系统。");// 内容

        //将相关信息存入数据库
        registerService.addRegister(username,checkCode,timestamp);

        javaMailSender.send(message);
        logger.info("文本邮件发送成功！");
        return true;
    }

    /**
     * 验证验证码的正确性和时间
     * @param params  "username" string    "checkCode" string
     * @return string "校验通过"/"验证码已失效，请重新验证。"/"验证码错误。"
     */
    //验证验证码 在有效期内且验证码正确返回校验通过
    @RequestMapping("/checkCode")
    public String checkCode(@RequestBody Map<String, String> params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        String username = jsonObject.getString("username");
        String checkCode = jsonObject.getString("checkCode");
        Register li = registerService.checkRegister(username,checkCode);
        if(li != null) {
            if(TimeUtil.cmpTime(li.getTimestamp())){
                registerService.deleteRegister(username);
                return "校验通过";
            }
            else {
                registerService.deleteRegister(username);
                return "验证码已失效，请重新验证。";
            }
        }
        return  "验证码错误。";
    }

}