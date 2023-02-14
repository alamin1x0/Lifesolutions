package com.lifesolutions.bd.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AgoraAccessToken {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}