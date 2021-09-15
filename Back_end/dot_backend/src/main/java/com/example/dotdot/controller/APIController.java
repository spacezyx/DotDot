package com.example.dotdot.controller;

import com.example.dotdot.aop.LimitRequest;
import com.example.dotdot.service.RobotService;
import com.example.dotdot.utils.apimsgutils.APIMsg;
import com.example.dotdot.utils.apimsgutils.APIMsgCode;
import com.example.dotdot.utils.apimsgutils.APIMsgUtil;
import com.example.dotdot.utils.apputils.APPUtil;
import com.example.dotdot.utils.httpclientutils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIController{
    Logger log= LoggerFactory.getLogger(QASystemController.class);
    @Autowired
    private RobotService robotService;

    /**
     * 数据库检索API接口
     * APIMsg:int status        String msg          String data;
     * @param question
     * @param apikey
     * @param timestamp
     * @param sign
     * @return APIMsg
     */
    @RequestMapping("/simple_search")
    @LimitRequest(count = 5)
    public APIMsg simple_search(@Param("question") String question,@Param("apikey") String apikey,
                                @Param("timestamp") String timestamp,@Param("sign") String sign
                                ) {
        log.info(question);
        log.info(apikey);
        log.info(timestamp);
        log.info(sign);

        String APISecret = robotService.getAPISecretByApikey(apikey);
        log.info(APISecret);
        //APIkey不存在
        if(APISecret==null)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.NO_APIKEY, APIMsgUtil.NO_APIKEY_MSG);

        //APIKey已禁用
        Boolean valid = robotService.getValidByApikey(apikey);
        log.info(valid.toString());
        if(!valid)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.DISABLED_PORT,APIMsgUtil.DISABLED_PORT_MSG);

        //请求超过次数限制
        Integer times = robotService.getTimesByApikey(apikey);
        log.info(times.toString());
        if(times<=0)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TOO_MANY_REQUESTS,APIMsgUtil.TOO_MANY_REQUESTS_MSG);

        //接口无权限
        Integer type = robotService.getTypeByApikey(apikey);
        if(type==2)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.MIANTAIN_PORT,APIMsgUtil.MIANTAIN_PORT_MSG);

        //问题为空
        if(question==null||timestamp==null||sign==null||question.equals("")){
            return APIMsgUtil.makeAPIMsg(APIMsgCode.OUTDATE_APIKEY,APIMsgUtil.OUTDATE_APIKEY_MSG);
        }

        String result = APPUtil.checkAuth(APISecret,timestamp,question,sign);
        log.info(result);
        //请求超时
        if(result.equals("Timeout"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TIME_OUT,APIMsgUtil.TIME_OUT_MSG);
        //签名错误
        else if(result.equals("Wrong sign"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.ACCESS_DENIED,APIMsgUtil.ACCESS_DENIED_MSG);
        //请求成功
        else if(result.equals("Success")) {
            String url = "http://127.0.0.1:5000/api/get/search?question="+question;
            //发送请求
            String data = HttpClientUtil.doGet(url);
            robotService.decreaseTimesByApikey(apikey);
            return APIMsgUtil.makeAPIMsg(APIMsgCode.SUCCESS,APIMsgUtil.SUCCESS_MSG,data);
        }
        return APIMsgUtil.makeAPIMsg(APIMsgCode.IP_BANNED,APIMsgUtil.IP_BANNED_MSG);

    }

    /**
     * 机器阅读理解接口
     * APIMsg:int status        String msg          String data;
     * @param question
     * @param apikey
     * @param timestamp
     * @param sign
     * @return APIMsg
     */
    @RequestMapping("/qa_mrc")
//    @LimitRequest(count = 5)
    public APIMsg qa_mrc(@Param("question") String question,@Param("apikey") String apikey,
                                @Param("timestamp") String timestamp,@Param("sign") String sign
    ) {
        log.info(question);
        log.info(apikey);
        log.info(timestamp);
        log.info(sign);

        String APISecret = robotService.getAPISecretByApikey(apikey);
        log.info(APISecret);
        //APIkey不存在
        if(APISecret==null)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.NO_APIKEY, APIMsgUtil.NO_APIKEY_MSG);

        //APIKey已禁用
        Boolean valid = robotService.getValidByApikey(apikey);
        log.info(valid.toString());
        if(!valid)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.DISABLED_PORT,APIMsgUtil.DISABLED_PORT_MSG);

        //请求超过次数限制
        Integer times = robotService.getTimesByApikey(apikey);
        log.info(times.toString());
        if(times<=0)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TOO_MANY_REQUESTS,APIMsgUtil.TOO_MANY_REQUESTS_MSG);

        //接口无权限
        Integer type = robotService.getTypeByApikey(apikey);
        log.info("type:"+type);
        if(type==1)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.MIANTAIN_PORT,APIMsgUtil.MIANTAIN_PORT_MSG);

        //问题为空
        if(question==null||timestamp==null||sign==null||question.equals("")){
            return APIMsgUtil.makeAPIMsg(APIMsgCode.OUTDATE_APIKEY,APIMsgUtil.OUTDATE_APIKEY_MSG);
        }

        String result = APPUtil.checkAuth(APISecret,timestamp,question,sign);
        log.info(result);
        //请求超时
        if(result.equals("Timeout"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TIME_OUT,APIMsgUtil.TIME_OUT_MSG);
            //签名错误
        else if(result.equals("Wrong sign"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.ACCESS_DENIED,APIMsgUtil.ACCESS_DENIED_MSG);
            //请求成功
        else if(result.equals("Success")) {
            String url = "http://127.0.0.1:5000/api/get/mrc?question="+question;
            //发送请求
            String data = HttpClientUtil.doGet(url);
            log.info("data from qamrc : "+data);
            robotService.decreaseTimesByApikey(apikey);
            return APIMsgUtil.makeAPIMsg(APIMsgCode.SUCCESS,APIMsgUtil.SUCCESS_MSG,data);
        }
        return APIMsgUtil.makeAPIMsg(APIMsgCode.IP_BANNED,APIMsgUtil.IP_BANNED_MSG);

    }

    /**
     * 数据库检索+机器阅读理解接口
     * APIMsg:int status        String msg          String data;
     * @param question
     * @param apikey
     * @param timestamp
     * @param sign
     * @return APIMsg
     */
    @RequestMapping("/qa_system")
//    @LimitRequest(count = 5)
    public APIMsg qa_system(@Param("question") String question,@Param("apikey") String apikey,
                         @Param("timestamp") String timestamp,@Param("sign") String sign
    ) {
        log.info(question);
        log.info(apikey);
        log.info(timestamp);
        log.info(sign);

        String APISecret = robotService.getAPISecretByApikey(apikey);
        log.info(APISecret);
        //APIkey不存在
        if(APISecret==null)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.NO_APIKEY, APIMsgUtil.NO_APIKEY_MSG);

        //APIKey已禁用
        Boolean valid = robotService.getValidByApikey(apikey);
        log.info(valid.toString());
        if(!valid)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.DISABLED_PORT,APIMsgUtil.DISABLED_PORT_MSG);

        //请求超过次数限制
        Integer times = robotService.getTimesByApikey(apikey);
        log.info(times.toString());
        if(times<=0)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TOO_MANY_REQUESTS,APIMsgUtil.TOO_MANY_REQUESTS_MSG);

        //接口无权限
        Integer type = robotService.getTypeByApikey(apikey);
        if(type!=3)
            return APIMsgUtil.makeAPIMsg(APIMsgCode.MIANTAIN_PORT,APIMsgUtil.MIANTAIN_PORT_MSG);

        //问题为空
        if(question==null||timestamp==null||sign==null||question.equals("")){
            return APIMsgUtil.makeAPIMsg(APIMsgCode.OUTDATE_APIKEY,APIMsgUtil.OUTDATE_APIKEY_MSG);
        }

        String result = APPUtil.checkAuth(APISecret,timestamp,question,sign);
        log.info(result);
        //请求超时
        if(result.equals("Timeout"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.TIME_OUT,APIMsgUtil.TIME_OUT_MSG);
            //签名错误
        else if(result.equals("Wrong sign"))
            return APIMsgUtil.makeAPIMsg(APIMsgCode.ACCESS_DENIED,APIMsgUtil.ACCESS_DENIED_MSG);
            //请求成功
        else if(result.equals("Success")) {
            String url = "http://127.0.0.1:5000/api/get/qasystem?question="+question;
            //发送请求
            String data = HttpClientUtil.doGet(url);
            log.info("data from qasystem : "+data);
            robotService.decreaseTimesByApikey(apikey);
            return APIMsgUtil.makeAPIMsg(APIMsgCode.SUCCESS,APIMsgUtil.SUCCESS_MSG,data);
        }
        return APIMsgUtil.makeAPIMsg(APIMsgCode.IP_BANNED,APIMsgUtil.IP_BANNED_MSG);
    }

}
