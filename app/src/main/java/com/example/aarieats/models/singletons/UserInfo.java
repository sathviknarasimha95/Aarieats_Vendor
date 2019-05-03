package com.example.aarieats.models.singletons;

import com.example.aarieats.models.User;

public class UserInfo {
    private static final UserInfo ourInstance = new UserInfo();

    public static UserInfo getInstance() {
        return ourInstance;
    }

    private User vendorInfo;

    private UserInfo() {
    }

    public void setVendorInfo(String userName,String email,String latLng) {
        User user = new User(email,userName,latLng);
        this.vendorInfo = user;
    }

    public User getVendorInfo() {
        return this.vendorInfo;
    }

}
