package com.lifesolutions.bd.kotlinCode.data.model;

public class CampusAmbassadorModel {

    String name;
    String address;
    String phone;
    String studyInfo;
    String bio;
    String image;
    String email;
    String timeStamp;
    String userId;


    public CampusAmbassadorModel() {
    }

    public CampusAmbassadorModel(String name, String address, String phone, String studyInfo, String bio, String image, String email, String timeStamp, String userId) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.studyInfo = studyInfo;
        this.bio = bio;
        this.image = image;
        this.email = email;
        this.timeStamp = timeStamp;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(String studyInfo) {
        this.studyInfo = studyInfo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

