package com.example.register;

public class Spacecraft {
    /*
    instance field
     */
    private int id;

    private String fromm;
    private String muda;
    private String amount;
    private String recieved_from;


    /*
    geter and seter
     */

    public String toString() {
        return fromm;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFromm(){
        return fromm;
    }
    public void setFromm(String fromm) {
        this.fromm = fromm;
    }

    public String getMuda() {
        return muda;
    }

    public void setMuda(String muda) {
        this.muda = muda;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRecieved_from() {
        return recieved_from;
    }

    public void setRecieved_from(String recieved_from) {
        this.recieved_from = recieved_from;
    }
}

