package com.lifesolutions.bd.Notifications;

import com.lifesolutions.bd.kotlinCode.data.model.NotificationData;

public class Sender {

    private NotificationData data;
    private String to;

    public Sender() {
    }

    public Sender(NotificationData data, String to) {
        this.data = data;
        this.to = to;
    }

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
