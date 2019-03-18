package com.example.aarieats.models.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.aarieats.http.HttpListner;
import com.example.aarieats.http.ProductListner;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.Products;
import com.example.aarieats.models.singletons.UserInfo;

import java.util.List;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<List<Products>> mProducts;

    public LiveData<List<Products>> getProducts() {
        if (mProducts == null) {
            mProducts = new MutableLiveData<List<Products>>();
            loadProducts();
        }
        return mProducts;
    }


    private void loadProducts() {
        ApiService apiService = ApiService.getInstance();
        apiService.getProducts(UserInfo.getInstance().getVendorInfo().getEmail(), new ProductListner() {


            @Override
            public void onSuccess(HttpListner.ResponseStatus status, List<Products> info) {
               mProducts.postValue(info);
            }

            @Override
            public void onFailure(HttpListner.ResponseStatus status, String info) {

            }
        });
    }
}
