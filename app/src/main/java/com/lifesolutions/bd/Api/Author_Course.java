package com.lifesolutions.bd.Api;

import com.google.gson.annotations.SerializedName;

public class Author_Course {
    @SerializedName("authorName")
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
