package com.example.aarieats.models;

public class User {

    private String mEmail;
    private String mUserName;
    private String mLatLng;

    public User(String email,String userName,String latLng) {
        this.mEmail = email;
        this.mUserName = userName;
        this.mLatLng = latLng;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getLatLng() {
        return mLatLng;
    }


}
