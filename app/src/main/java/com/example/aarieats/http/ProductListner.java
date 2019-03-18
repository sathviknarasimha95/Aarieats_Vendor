package com.example.aarieats.http;

import com.example.aarieats.models.Products;

import java.util.List;

public interface ProductListner {

    enum ResponseStatus {
        SUCCESS,
        FAILURE
    }
    void onSuccess(HttpListner.ResponseStatus status, List<Products> info);
    void onFailure(HttpListner.ResponseStatus status, String info);
}
