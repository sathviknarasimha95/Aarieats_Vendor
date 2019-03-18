package com.example.aarieats.models;

public class User {

    private String mEmail;
    private String mUserName;

    public User(String email,String userName) {
        this.mEmail = email;
        this.mUserName = userName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getUserName() {
        return mUserName;
    }



}
