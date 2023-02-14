package com.lifesolutions.bd.Models;

public class LastConversation {
    private String userId,message;
    private long timeStamp;

    public LastConversation() {
    }

    public LastConversation(String userId, String message, long timeStamp) {
        this.userId = userId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
