package com.example.aarieats.http.models;

import com.example.aarieats.models.Order;
import com.example.aarieats.models.Products;

import java.util.List;

public class GetOrderResponse {

    String error;

    List<Order> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
