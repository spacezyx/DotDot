package com.example.dotdot.controller;


import com.example.dotdot.utils.httpclientutils.HttpClientUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QASystemController {
    Logger log= LoggerFactory.getLogger(QASystemController.class);

    /**
     * QASystem
     * @param params  "question" string
     * @return string
     */
    @RequestMapping("/QASystem")
    public String QASystem(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String url = "http://127.0.0.1:5000/api/get/qasystem?question="+question;
        //发送请求
        String s = HttpClientUtil.doGet(url);
        return s;
    }

    /**
     * QASearch
     * @param params  "question" string
     * @return string
     */
    @RequestMapping("/QASearch")
    public String QASearch(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String url = "http://127.0.0.1:5000/api/get/search?question="+question;
        //发送请求
        String s = HttpClientUtil.doGet(url);
        return s;
    }

    /**
     * QAMRC
     * @param params  "question" string
     * @return string
     */
    @RequestMapping("/QAMRC")
    public String QAMRC(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        String question = jsonObject.getString("question");
        String url = "http://127.0.0.1:5000/api/get/mrc?question="+question;
        //发送请求
        String s = HttpClientUtil.doGet(url);
        return s;
    }
}
