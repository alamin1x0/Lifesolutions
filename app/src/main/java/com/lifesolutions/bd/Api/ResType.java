package com.lifesolutions.bd.Api;

import com.google.gson.annotations.SerializedName;

public class ResType {

    // Response SerializedName..
    @SerializedName("message")
    private String message;


    // Post..
    private String fileUrl;

    public ResType(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    // Response..

    public String getMessage() {
        return message;
    }
}
