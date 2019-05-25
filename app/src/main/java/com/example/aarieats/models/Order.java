package com.example.aarieats.models;

public class Order {

    private String OrderId;

    private String UserId;

    private String VendorId;

    private String UserEmail;

    private String VendorEmail;

    private String OrderStatus;

    private String UserLatLng;

    private String PaymentType;

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public Order(String mOrderId, String mUserId, String mUserEmail, String mVendorId, String mVendorEmail, String mOrderStatus, String mOrderLatLng, String paymentType) {
        this.OrderId = mOrderId;
        this.UserId = mUserId;
        this.UserEmail = mUserEmail;
        this.VendorId = mVendorId;
        this.VendorEmail = mVendorEmail;
        this.OrderStatus = mOrderStatus;
        this.UserLatLng = mOrderLatLng;
        this.PaymentType = paymentType;
    }

    public String getOrderId() {
        return OrderId;
    }

    public String getUserId() {
        return UserId;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getVendorId() {
        return VendorId;
    }

    public String getVendorEmail() {
        return VendorEmail;
    }

    public void setOrderId(String mOrderId) {
        this.OrderId = mOrderId;
    }

    public void setUserId(String mUserId) {
        this.UserId = mUserId;
    }

    public void setUserEmail(String mUserEmail) {
        this.UserEmail = mUserEmail;
    }

    public void setVendorId(String mVendorId) {
        this.VendorId = mVendorId;
    }

    public void setVendorEmail(String mVendorEmail) {
        this.VendorEmail = mVendorEmail;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public String getUserLatLng() {
        return UserLatLng;
    }

    public void setUserLatLng(String ordLatLng) {
        UserLatLng = ordLatLng;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
