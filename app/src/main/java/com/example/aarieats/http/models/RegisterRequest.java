package com.example.aarieats.http.models;

public class RegisterRequest {

    private String vendorname;

    private String username;

    private String password;

    private String email;

    private String address;

    private String city;

    private String state;

    private String phno;

    private String lat;

    private String lng;

    public RegisterRequest(String vendorname,
                           String username,
                           String password,
                           String email,
                           String address,
                           String city,
                           String state,
                           String phno,
                           String lat,
                           String lng) {
        this.vendorname = vendorname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phno = phno;
        this.lat = lat;
        this.lng = lng;
    }
}
