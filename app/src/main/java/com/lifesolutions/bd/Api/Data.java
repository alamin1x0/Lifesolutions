package com.lifesolutions.bd.Api;

import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("courseName")
    private String courseName;

    @SerializedName("image")
    private String image;

    @SerializedName("price")
    private String price;

    @SerializedName("author")
    private Author_Course author;

    @SerializedName("_id")
    private String courseID;


    public String getCourseID() {
        return courseID;
    }



    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }



    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Author_Course getAuthor() {
        return author;
    }

    public void setAuthor(Author_Course author) {
        this.author = author;
    }
}
