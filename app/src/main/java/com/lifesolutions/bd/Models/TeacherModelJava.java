package com.lifesolutions.bd.Models;

public class TeacherModelJava {

    String _id;
    String firstName;
    String lastName;
    String profileImage;
    Double ratings;
    int rated;
    int talktime;


    public TeacherModelJava() {
    }


    public TeacherModelJava(String _id, String firstName, String lastName, String profileImage, Double ratings, int rated, int talktime) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.ratings = ratings;
        this.rated = rated;
        this.talktime = talktime;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public int getTalktime() {
        return talktime;
    }

    public void setTalktime(int talktime) {
        this.talktime = talktime;
    }
}
