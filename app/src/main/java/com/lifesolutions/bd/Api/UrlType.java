package com.lifesolutions.bd.Api;

import com.google.gson.annotations.SerializedName;

public class UrlType {

    // Response SerializedName..
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("linkUrl")
    private String linkUrl;

    @SerializedName("source")
    private String source;

    @SerializedName("message")
    private String message;


    // Post..
    private String url;

    public UrlType(String url) {
        this.url = url;
    }

    // Response..

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
