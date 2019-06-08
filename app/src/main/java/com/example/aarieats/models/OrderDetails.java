package com.example.aarieats.models;

public class OrderDetails {

    private String OrderId;

    private String ProductId;

    private String ProductName;

    private String ProductPrice;

    private String Units;

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductPrice() {
        return Integer.parseInt(ProductPrice);
    }
}
