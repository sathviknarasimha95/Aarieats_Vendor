package com.example.aarieats.http;

public interface HttpListner {

    enum ResponseStatus {
        LOGIN_SUCCESS,
        LOGIN_AUTHENTICATION_FAILURE,
        LOGIN_NETWORK_FAILURE,
        REGISTER_SUCCESS,
        LOGIN_INTERNAL_ERROR,
        SUCCESS,
        FAILURE
    }
    void onSuccess(ResponseStatus status, String info);
    void onFailure(ResponseStatus status, String info);
}