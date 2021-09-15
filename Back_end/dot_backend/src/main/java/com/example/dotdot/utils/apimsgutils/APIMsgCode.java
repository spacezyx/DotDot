package com.example.dotdot.utils.apimsgutils;


public enum APIMsgCode {
    SUCCESS(APIMsgUtil.SUCCESS, APIMsgUtil.SUCCESS_MSG),
    NO_APIKEY(APIMsgUtil.NO_APIKEY,APIMsgUtil.NO_APIKEY_MSG),
    OUTDATE_APIKEY(APIMsgUtil.OUTDATE_APIKEY,APIMsgUtil.OUTDATE_APIKEY_MSG),
    ACCESS_DENIED(APIMsgUtil.ACCESS_DENIED,APIMsgUtil.ACCESS_DENIED_MSG),
    TOO_MANY_REQUESTS(APIMsgUtil.TOO_MANY_REQUESTS,APIMsgUtil.TOO_MANY_REQUESTS_MSG),
    IP_BANNED(APIMsgUtil.IP_BANNED,APIMsgUtil.IP_BANNED_MSG),
    TIME_OUT(APIMsgUtil.TIME_OUT,APIMsgUtil.TIME_OUT_MSG),
    MIANTAIN_PORT(APIMsgUtil.MIANTAIN_PORT,APIMsgUtil.MIANTAIN_PORT_MSG),
    DISABLED_PORT(APIMsgUtil.DISABLED_PORT,APIMsgUtil.DISABLED_PORT_MSG);

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getAPIMsg() {
        return msg;
    }

    private APIMsgCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
