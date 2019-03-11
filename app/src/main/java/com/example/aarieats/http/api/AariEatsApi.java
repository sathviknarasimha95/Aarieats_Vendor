package com.example.aarieats.http.api;

import com.example.aarieats.http.models.LoginRequest;
import com.example.aarieats.http.models.LoginResponse;
import com.example.aarieats.http.models.RegisterRequest;
import com.example.aarieats.http.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AariEatsApi {

    @POST("/loginvendor")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/registervendor")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
