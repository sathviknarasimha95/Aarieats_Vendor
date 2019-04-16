package com.example.aarieats.http.models;

public class GetOrderRequest {

    private String email;

    public GetOrderRequest(String mVendorEmail) {
        this.email = mVendorEmail;
    }
}
