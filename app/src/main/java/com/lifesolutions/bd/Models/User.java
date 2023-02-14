package com.lifesolutions.bd.Models;

public class User{
    private String firstName,lastName,address,uID,imageThumbnail;
    private int points;

    public User() {
    }

    public User(String firstName, String lastName, String address, String uID, String imageThumbnail, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.uID = uID;
        this.imageThumbnail = imageThumbnail;
        this.points = points;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
