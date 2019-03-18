package com.example.aarieats.http.models;

public class AddProductsRequest {

    private String email;

    private String productId;

    private String productName;

    private String productType;

    private String productUnit;

    private String productPrice;

    public AddProductsRequest(String email, String productId, String productName, String productType, String productUnit, String productPrice) {
        this.email = email;
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productUnit = productUnit;
        this.productPrice = productPrice;
    }
}
