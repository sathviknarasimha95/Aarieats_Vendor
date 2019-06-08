package com.example.aarieats.http.api;

import com.example.aarieats.http.models.AddProductResponse;
import com.example.aarieats.http.models.AddProductsRequest;
import com.example.aarieats.http.models.GetOrderDetailsRequest;
import com.example.aarieats.http.models.GetOrderDetailsResponse;
import com.example.aarieats.http.models.GetOrderRequest;
import com.example.aarieats.http.models.GetOrderResponse;
import com.example.aarieats.http.models.GetProductRequest;
import com.example.aarieats.http.models.GetProductResponse;
import com.example.aarieats.http.models.LoginRequest;
import com.example.aarieats.http.models.LoginResponse;
import com.example.aarieats.http.models.RegisterRequest;
import com.example.aarieats.http.models.RegisterResponse;
import com.example.aarieats.http.models.UpdateOrderRequest;
import com.example.aarieats.http.models.UpdateOrderResponse;

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

    @POST("/getordervendor")
    Call<GetOrderResponse> getOrders(@Body GetOrderRequest getOrderRequest);

    @POST("/getvendororderdetails")
    Call<GetOrderDetailsResponse> getOrderDetails(@Body GetOrderDetailsRequest getOrderDetailsRequest);

    @POST("/updateOrder")
    Call<UpdateOrderResponse> updateOrder(@Body UpdateOrderRequest updateOrderRequest);

    @POST("/getorderhistoryvendor")
    Call<GetOrderResponse> getOrderHistory(@Body GetOrderRequest getOrderRequest);

    @POST("/getpaymenthistory")
    Call<GetOrderResponse> getPaymentHistory(@Body GetOrderRequest getOrderRequest);

}
