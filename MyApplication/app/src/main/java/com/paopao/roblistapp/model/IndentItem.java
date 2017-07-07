package com.paopao.roblistapp.model;

/**
 * Created by Administrator on 2017/7/5.
 */

public class IndentItem {
    private String state;//订单状态（0为正常单，1为问题单,2为悬赏单）
    private String uuid;//单号（订单的唯一标识）
    private String type;//订单类型
    private String completeness;//订单完成情况（notake（未取件）、undone（未完成）、finish（已完成）三种，）
    private String sendlocation;//(随意送的寄件地点，随意购的购买地点，随意办的办事地点)
    private String receivelocation;//(随意送的收件地点，随意购的收货地点，随意办的交接地点)
    private String sendphonenumber;
    private String receivephonenumber;
    private String money;
    private String startstate;//订单开始状态（1为实时单，0为预约单）
    private String goodsname;
    private String goodsweight;
    private String customerremark;//顾客备注
    private String remark;//跑腿师傅备注
    private String date;//接单时间
    private String location;//距离

    public IndentItem() {
    }

    public IndentItem(String state, String uuid, String type, String completeness, String sendlocation, String receivelocation, String sendphonenumber, String receivephonenumber, String money, String startstate, String goodsname, String goodsweight, String customerremark, String remark, String date, String location) {
        this.state = state;
        this.uuid = uuid;
        this.type = type;
        this.completeness = completeness;
        this.sendlocation = sendlocation;
        this.receivelocation = receivelocation;
        this.sendphonenumber = sendphonenumber;
        this.receivephonenumber = receivephonenumber;
        this.money = money;
        this.goodsname = goodsname;
        this.goodsweight = goodsweight;
        this.customerremark = customerremark;
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompleteness() {
        return completeness;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    public String getSendlocation() {
        return sendlocation;
    }

    public void setSendlocation(String sendlocation) {
        this.sendlocation = sendlocation;
    }

    public String getReceivelocation() {
        return receivelocation;
    }

    public void setReceivelocation(String receivelocation) {
        this.receivelocation = receivelocation;
    }

    public String getSendphonenumber() {
        return sendphonenumber;
    }

    public void setSendphonenumber(String sendphonenumber) {
        this.sendphonenumber = sendphonenumber;
    }

    public String getReceivephonenumber() {
        return receivephonenumber;
    }

    public void setReceivephonenumber(String receivephonenumber) {
        this.receivephonenumber = receivephonenumber;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStartstate() {
        return startstate;
    }

    public void setStartstate(String startstate) {
        this.startstate = startstate;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsweight() {
        return goodsweight;
    }

    public void setGoodsweight(String goodsweight) {
        this.goodsweight = goodsweight;
    }

    public String getCustomerremark() {
        return customerremark;
    }

    public void setCustomerremark(String customerremark) {
        this.customerremark = customerremark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
