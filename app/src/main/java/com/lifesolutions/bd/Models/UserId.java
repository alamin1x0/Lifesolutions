package com.lifesolutions.bd.Models;

public class UserId {
    private String type,uid;

    public UserId() {
    }

    public UserId(String type, String uid) {
        this.type = type;
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
