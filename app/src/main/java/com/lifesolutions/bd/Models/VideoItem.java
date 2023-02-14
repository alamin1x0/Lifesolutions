package com.lifesolutions.bd.Models;

public class VideoItem {

    private String title,description,imageUrl,videoId;

    public VideoItem() {
    }

    public VideoItem(String title, String description, String imageUrl, String videoId) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
