package com.example.aarieats.http;

import com.example.aarieats.models.OrderDetails;

import java.util.List;

public interface GetOrderDetailsListner {

    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, List<OrderDetails> info);
    void onFailure(ResponseStatus status, String info);
}
