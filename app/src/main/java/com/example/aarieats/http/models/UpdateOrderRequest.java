package com.example.aarieats.http.models;

public class UpdateOrderRequest {

    private String orderId;

    private int statusCode;

    public UpdateOrderRequest(String orderId, int statusCode) {
        this.orderId = orderId;
        this.statusCode = statusCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
