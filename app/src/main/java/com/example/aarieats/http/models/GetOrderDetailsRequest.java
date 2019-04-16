package com.example.aarieats.http.models;

public class GetOrderDetailsRequest {

    private String orderId;

    private String email;

    public GetOrderDetailsRequest(String orderId,String email) {
        this.orderId = orderId;
        this.email = email;
    }

}
