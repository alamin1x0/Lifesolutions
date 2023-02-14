package com.lifesolutions.bd.Models;

public class CreateUserByGoogle {
    private String email,phone,firstName,lastName,password,referCode,role,imageThumbnail,imageMedium,imageHD,uID,registrationType,deviceId,ipAddress;
    private long registeredTime;
    private boolean permission,randomCall,referable;
    private int points,referred;

    public CreateUserByGoogle() {
    }

    public CreateUserByGoogle(String email, String phone, String firstName, String lastName, String password, String referCode, String role, String imageThumbnail, String imageMedium, String imageHD, String uID, String registrationType, String deviceId, String ipAddress, long registeredTime, boolean permission, boolean randomCall, boolean referable, int points, int referred) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.referCode = referCode;
        this.role = role;
        this.imageThumbnail = imageThumbnail;
        this.imageMedium = imageMedium;
        this.imageHD = imageHD;
        this.uID = uID;
        this.registrationType = registrationType;
        this.deviceId = deviceId;
        this.ipAddress = ipAddress;
        this.registeredTime = registeredTime;
        this.permission = permission;
        this.randomCall = randomCall;
        this.referable = referable;
        this.points = points;
        this.referred = referred;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public String getImageHD() {
        return imageHD;
    }

    public void setImageHD(String imageHD) {
        this.imageHD = imageHD;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(long registeredTime) {
        this.registeredTime = registeredTime;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isRandomCall() {
        return randomCall;
    }

    public void setRandomCall(boolean randomCall) {
        this.randomCall = randomCall;
    }

    public boolean isReferable() {
        return referable;
    }

    public void setReferable(boolean referable) {
        this.referable = referable;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getReferred() {
        return referred;
    }

    public void setReferred(int referred) {
        this.referred = referred;
    }
}
