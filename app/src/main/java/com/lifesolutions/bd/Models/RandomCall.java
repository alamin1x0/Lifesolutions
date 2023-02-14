package com.lifesolutions.bd.Models;

public class RandomCall {
    String callerId;

    public RandomCall() {
    }

    public RandomCall(String callerId) {
        this.callerId = callerId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }
}
