package com.example.aarieats.http;

public interface UpdateOrderListner {
    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }
    void onSuccess(ResponseStatus status, String info);
    void onFailure(ResponseStatus status, String info);
}
