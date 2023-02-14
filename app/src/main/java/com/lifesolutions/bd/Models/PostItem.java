package com.lifesolutions.bd.Models;

public class PostItem {
    private String authorID,postDescription,type,postThumbnail,postImage,postID,privacy;
    private long date;
    private boolean permission;

    public PostItem() {

    }

    public PostItem(String authorID, String postDescription, String type, String postThumbnail, String postImage, String postID, String privacy, long date, boolean permission) {
        this.authorID = authorID;
        this.postDescription = postDescription;
        this.type = type;
        this.postThumbnail = postThumbnail;
        this.postImage = postImage;
        this.postID = postID;
        this.privacy = privacy;
        this.date = date;
        this.permission = permission;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostThumbnail() {
        return postThumbnail;
    }

    public void setPostThumbnail(String postThumbnail) {
        this.postThumbnail = postThumbnail;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
