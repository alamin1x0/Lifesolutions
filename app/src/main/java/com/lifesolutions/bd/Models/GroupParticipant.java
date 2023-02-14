package com.lifesolutions.bd.Models;

public class GroupParticipant {
    private String uID,role;
    private long timestamp;

    public GroupParticipant() {
    }

    public GroupParticipant(String uID, String role, long timestamp) {
        this.uID = uID;
        this.role = role;
        this.timestamp = timestamp;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
