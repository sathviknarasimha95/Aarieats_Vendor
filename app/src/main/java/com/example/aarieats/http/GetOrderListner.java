package com.example.aarieats.http;

import com.example.aarieats.models.Order;
import com.example.aarieats.models.Products;

import java.util.List;

public interface GetOrderListner {

    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, List<Order> info);
    void onFailure(ResponseStatus status, String info);

}
