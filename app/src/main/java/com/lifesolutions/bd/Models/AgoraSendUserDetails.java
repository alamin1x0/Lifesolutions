package com.lifesolutions.bd.Models;

public class AgoraSendUserDetails {
    String channelName;
    Integer uid;

    public AgoraSendUserDetails(String channelName, Integer uid) {
        this.channelName = channelName;
        this.uid = uid;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
