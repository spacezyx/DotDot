package com.example.dotdot.controller;

import com.example.dotdot.utils.timeutils.TimeUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {
//    Logger log= LoggerFactory.getLogger(QASystemController.class);

    /**
     * 获取simple_search的url
     * @param params  "question" string    "apikey" string    "apisecret" string
     * @return string url
     */
    @RequestMapping("/getUrlForSimple_search")
    public String getUrlForSimple_search(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String apikey = jsonObject.getString("apikey");
        String apisecret = jsonObject.getString("apisecret");
        String timestamp = TimeUtil.getTime();
        String tmp = apisecret+question+timestamp;
        String m1d5 = DigestUtils.md5DigestAsHex(tmp.getBytes());
        String m2d5 = DigestUtils.md5DigestAsHex(m1d5.getBytes());
        String sign = DigestUtils.md5DigestAsHex(m2d5.getBytes());
        System.out.println("timestamp is : "+timestamp);
        System.out.println("生成的sign : "+sign);
        String result = "http://124.70.130.38:8080/simple_search?" +
                "question=" +question+
                "&apikey=" +apikey+
                "&timestamp=" +timestamp+
                "&sign="+sign;
        return result;
    }

    /**
     * 获取qa_mrc的url
     * @param params  "question" string    "apikey" string    "apisecret" string
     * @return string url
     */
    @RequestMapping("/getUrlForQa_mrc")
    public String getUrlForQa_mrc(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String apikey = jsonObject.getString("apikey");
        String apisecret = jsonObject.getString("apisecret");
        String timestamp = TimeUtil.getTime();
        String tmp = apisecret+question+timestamp;
        String m1d5 = DigestUtils.md5DigestAsHex(tmp.getBytes());
        String m2d5 = DigestUtils.md5DigestAsHex(m1d5.getBytes());
        String sign = DigestUtils.md5DigestAsHex(m2d5.getBytes());
        System.out.println("timestamp is : "+timestamp);
        System.out.println("生成的sign : "+sign);
        String result = "http://124.70.130.38:8080/qa_mrc?" +
                "question=" +question+
                "&apikey=" +apikey+
                "&timestamp=" +timestamp+
                "&sign="+sign;
        return result;
    }

    /**
     * 获取qasystem的url
     * @param params  "question" string    "apikey" string    "apisecret" string
     * @return string url
     */
    @RequestMapping("/getUrlForQa_system")
    public String getUrlForQa_system(@RequestBody Object params){
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String apikey = jsonObject.getString("apikey");
        String apisecret = jsonObject.getString("apisecret");
        String timestamp = TimeUtil.getTime();
        String tmp = apisecret+question+timestamp;
        String m1d5 = DigestUtils.md5DigestAsHex(tmp.getBytes());
        String m2d5 = DigestUtils.md5DigestAsHex(m1d5.getBytes());
        String sign = DigestUtils.md5DigestAsHex(m2d5.getBytes());
        System.out.println("timestamp is : "+timestamp);
        System.out.println("生成的sign : "+sign);
        String result = "http://124.70.130.38:8080/qa_system?" +
                "question=" +question+
                "&apikey=" +apikey+
                "&timestamp=" +timestamp+
                "&sign="+sign;
        return result;
    }
}
