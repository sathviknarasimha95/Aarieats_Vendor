package com.example.aarieats.http.models;

import com.example.aarieats.models.Products;

import java.util.List;

public class GetProductResponse {

    String error;

    List<Products> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Products> getData() {
        return data;
    }

    public void setData(List<Products> data) {
        this.data = data;
    }
}
