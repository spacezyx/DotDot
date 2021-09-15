package com.example.dotdot.utils.apimsgutils;

public class APIMsg {
    private int status;
    private String msg;
    private String data;

    APIMsg(APIMsgCode msg, String extra, String data){
        this.status = msg.getStatus();
        this.msg = extra;
        this.data = data;
    }

    APIMsg(APIMsgCode msg){
        this.status = msg.getStatus();
        this.msg = msg.getAPIMsg();
        this.data = null;
    }

    APIMsg(APIMsgCode msg, String extra){
        this.status = msg.getStatus();
        this.msg = extra;
        this.data = null;
    }

    APIMsg(int status, String extra, String data){
        this.status = status;
        this.msg = extra;
        this.data = data;
    }

    APIMsg(int status, String extra){
        this.status = status;
        this.msg = extra;
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAPIMsg() {
        return msg;
    }

    public void setAPIMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }






}
