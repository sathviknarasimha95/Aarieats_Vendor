package com.example.aarieats.http.api;

import android.util.Log;

import com.example.aarieats.http.AddProductListner;
import com.example.aarieats.http.HttpListner;
import com.example.aarieats.http.ProductListner;
import com.example.aarieats.http.ServiceGenerator;
import com.example.aarieats.http.models.AddProductResponse;
import com.example.aarieats.http.models.AddProductsRequest;
import com.example.aarieats.http.models.GetProductRequest;
import com.example.aarieats.http.models.GetProductResponse;
import com.example.aarieats.http.models.LoginRequest;
import com.example.aarieats.http.models.LoginResponse;
import com.example.aarieats.http.models.RegisterRequest;
import com.example.aarieats.http.models.RegisterResponse;
import com.example.aarieats.models.singletons.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    private static final ApiService ourInstance = new ApiService();

    public static ApiService getInstance() {
        return ourInstance;
    }

    private ApiService() {
    }

    public void login(String username, String password, final HttpListner httpListner) {
        LoginRequest loginRequest = new LoginRequest(username,password);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<LoginResponse> loginCall = aariEatsApi.login(loginRequest);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                    httpListner.onSuccess(HttpListner.ResponseStatus.LOGIN_SUCCESS, "sussess");
                } else if(response.code() == 204) {
                    httpListner.onSuccess(HttpListner.ResponseStatus.LOGIN_AUTHENTICATION_FAILURE, "Authentication failure");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                httpListner.onFailure(HttpListner.ResponseStatus.LOGIN_NETWORK_FAILURE,t.getMessage());
            }
        });
    }

    public void register(String vendorname, String username,String password,String email,String address,String city,String state,String phno,String lat,String lng, final HttpListner httpListner) {
        RegisterRequest registerRequest = new RegisterRequest(vendorname,username,password,email,address,city,state,phno,lat,lng);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<RegisterResponse> registerCall = aariEatsApi.register(registerRequest);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                        httpListner.onSuccess(HttpListner.ResponseStatus.REGISTER_SUCCESS, "sussess");
                }else {
                        httpListner.onSuccess(HttpListner.ResponseStatus.FAILURE, "Register failure");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    httpListner.onFailure(HttpListner.ResponseStatus.FAILURE,"Register failure");
            }
        });
    }

    public void getProducts(String email, final ProductListner httpListner) {
        GetProductRequest getProductRequest = new GetProductRequest(email);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<GetProductResponse> getProductsCall = aariEatsApi.getProducts(getProductRequest);
        getProductsCall.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                Log.i("apiservice",response.body()+"");
                if(response.code() == 200) {
                    httpListner.onSuccess(HttpListner.ResponseStatus.REGISTER_SUCCESS, response.body().getData());
                } else {
                    httpListner.onSuccess(HttpListner.ResponseStatus.FAILURE, null);
                }
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                httpListner.onFailure(HttpListner.ResponseStatus.FAILURE, t.getMessage());
            }
        });
    }

    public void addProducts(String productId, String productName, String productType, String productPrice, String productUnit, final AddProductListner addProductListner) {
        String email = UserInfo.getInstance().getVendorInfo().getEmail();
        AddProductsRequest addProductsRequest = new AddProductsRequest(email,productId,productName,productType,productUnit,productPrice);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<AddProductResponse> addProductCall =aariEatsApi.addProducts(addProductsRequest);
        addProductCall.enqueue(new Callback<AddProductResponse>() {
            @Override
            public void onResponse(Call<AddProductResponse> call, Response<AddProductResponse> response) {
                if(response.code() == 200) {
                    addProductListner.onSuccess(AddProductListner.ResponseStatus.SUCCESS,response.body().getData());
                } else {
                    addProductListner.onFailure(AddProductListner.ResponseStatus.INVALID_PARAMETERS,"Invalid Parameter");
                }
            }

            @Override
            public void onFailure(Call<AddProductResponse> call, Throwable t) {
                addProductListner.onFailure(AddProductListner.ResponseStatus.FAILURE,t.getMessage());
            }
        });
    }
}
