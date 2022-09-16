package com.example.ssmdemo.bean;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private int code;
    private String state;
    private Map<String,Object> map = new HashMap<>();

    public static Message success(){
        Message message = new Message();
        message.code=100;
        message.state="处理成功";
        return message;
    }

    public static Message fail(){
        Message message = new Message();
        message.code=100;
        message.state="处理失败";
        return message;
    }

    public Message add(String name,Object obj){
        this.getMap().put(name,obj);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
