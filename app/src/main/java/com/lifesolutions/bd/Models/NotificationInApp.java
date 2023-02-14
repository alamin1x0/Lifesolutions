package com.lifesolutions.bd.Models;

public class NotificationInApp {
    private String postId,type,sender,receiver,notification,notificationId;
    private long time;
    private boolean isSeen;

    public NotificationInApp() {
    }

    public NotificationInApp(String postId, String type, String sender, String receiver, String notification, String notificationId, long time, boolean isSeen) {
        this.postId = postId;
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.notification = notification;
        this.notificationId = notificationId;
        this.time = time;
        this.isSeen = isSeen;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
