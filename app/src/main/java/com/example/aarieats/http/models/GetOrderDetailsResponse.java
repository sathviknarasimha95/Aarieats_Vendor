package com.example.aarieats.http.models;

import com.example.aarieats.models.OrderDetails;

import java.util.List;

public class GetOrderDetailsResponse {

    String error;

    List<OrderDetails> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<OrderDetails> getData() {
        return data;
    }

    public void setData(List<OrderDetails> data) {
        this.data = data;
    }
}
