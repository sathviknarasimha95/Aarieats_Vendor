package com.example.aarieats.http;

import com.example.aarieats.models.Products;

import java.util.List;

public interface AddProductListner {

    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }
    void onSuccess(ResponseStatus status, String info);
    void onFailure(ResponseStatus status, String info);
}
