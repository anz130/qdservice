package com.paopao.roblistapp.model;

/**
 * Created by Administrator on 2017/6/21.
 */

public class IndentInfo {
    private String state;//订单状态（0为正常单，1为问题单）
    private String sendLocation;
    private String receiveLocation;
    private String price;
    private String date;

    private String distance;

    public String getCompleteness() {
        return completeness;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    private String completeness;//订单完成情况

    public String getStartstate() {
        return startstate;
    }

    public void setStartstate(String startstate) {
        this.startstate = startstate;
    }

    private String startstate;

    public IndentInfo(String sendLocation, String receiveLocation, String price, String date, String state, String distance,String startstate) {
        this.sendLocation = sendLocation;
        this.receiveLocation = receiveLocation;
        this.price = price;
        this.date = date;
        this.state = state;
        this.distance = distance;
        this.startstate = startstate;
    }

    public String getSendLocation() {
        return sendLocation;
    }

    public void setSendLocation(String sendLocation) {
        this.sendLocation = sendLocation;
    }

    public String getReceiveLocation() {
        return receiveLocation;
    }

    public void setReceiveLocation(String receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
