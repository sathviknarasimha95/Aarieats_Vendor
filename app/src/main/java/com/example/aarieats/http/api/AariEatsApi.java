package com.example.aarieats.http.api;

import com.example.aarieats.http.models.AddProductResponse;
import com.example.aarieats.http.models.AddProductsRequest;
import com.example.aarieats.http.models.GetProductRequest;
import com.example.aarieats.http.models.GetProductResponse;
import com.example.aarieats.http.models.LoginRequest;
import com.example.aarieats.http.models.LoginResponse;
import com.example.aarieats.http.models.RegisterRequest;
import com.example.aarieats.http.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AariEatsApi {

    @POST("/loginvendor")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/registervendor")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("/getproducts")
    Call<GetProductResponse> getProducts(@Body GetProductRequest getProductRequest);

    @POST("/addproducts")
    Call<AddProductResponse> addProducts(@Body AddProductsRequest addProductsRequest);


}
