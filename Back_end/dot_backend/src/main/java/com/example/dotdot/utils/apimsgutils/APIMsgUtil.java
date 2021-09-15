package com.example.dotdot.utils.apimsgutils;

public class APIMsgUtil {

    public static final int SUCCESS = 101;
    public static final int NO_APIKEY = 101;
    public static final int OUTDATE_APIKEY = 102;
    public static final int ACCESS_DENIED = 103;
    public static final int TOO_MANY_REQUESTS = 104;
    public static final int IP_BANNED = 105;
    public static final int TIME_OUT = 106;
    public static final int MIANTAIN_PORT = 107;
    public static final int DISABLED_PORT = 108;

    public static final String SUCCESS_MSG = "请求成功";
    public static final String NO_APIKEY_MSG = "APPKEY为空或不存在";
    public static final String OUTDATE_APIKEY_MSG = "请求参数错误";
    public static final String ACCESS_DENIED_MSG = "签名错误";
    public static final String TOO_MANY_REQUESTS_MSG = "请求超过次数限制";
    public static final String IP_BANNED_MSG = "发生未知错误，请联系开发人员";
    public static final String TIME_OUT_MSG = "签名过期";
    public static final String MIANTAIN_PORT_MSG = "接口无权限";
    public static final String DISABLED_PORT_MSG = "接口已停用";


    public static APIMsg makeAPIMsg(APIMsgCode code, String msg, String data){
        return new APIMsg(code, msg, data);
    }

    public static APIMsg makeAPIMsg(APIMsgCode code){
        return new APIMsg(code);
    }

    public static APIMsg makeAPIMsg(APIMsgCode code, String msg){
        return new APIMsg(code, msg);
    }

    public static APIMsg makeAPIMsg(int status, String msg, String data){
        return new APIMsg(status, msg, data);
    }

    public static APIMsg makeAPIMsg(int status, String msg){
        return new APIMsg(status, msg);
    }
}
