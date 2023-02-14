package com.lifesolutions.bd.Models;

public class RandomCallUserItem {
    private String uID;
    private Boolean randomCall;

    public RandomCallUserItem() {
    }

    public RandomCallUserItem(String uID, Boolean randomCall) {
        this.uID = uID;
        this.randomCall = randomCall;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public Boolean getRandomCall() {
        return randomCall;
    }

    public void setRandomCall(Boolean randomCall) {
        this.randomCall = randomCall;
    }
}
