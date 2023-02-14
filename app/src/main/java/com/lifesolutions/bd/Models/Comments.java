package com.lifesolutions.bd.Models;

public class Comments {
    private String comment,commenterId,commentId,postId;
    private long dateTime;
    private Boolean permission;

    public Comments() {
    }

    public Comments(String comment, String commenterId, String commentId, String postId, long dateTime, Boolean permission) {
        this.comment = comment;
        this.commenterId = commenterId;
        this.commentId = commentId;
        this.postId = postId;
        this.dateTime = dateTime;
        this.permission = permission;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }
}
