package com.example.aarieats.http.models;

public class GetProductRequest {

    String vendorEmail;

    public GetProductRequest(String email) {
        this.vendorEmail = email;
    }
}
