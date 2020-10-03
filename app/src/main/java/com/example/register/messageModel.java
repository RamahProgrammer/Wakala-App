package com.example.register;

public class messageModel {
    private String sms,from;



    public messageModel(String sms, String from,String id) {
        this.sms = sms;
        this.from = from;

    }

    public messageModel() {
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}

