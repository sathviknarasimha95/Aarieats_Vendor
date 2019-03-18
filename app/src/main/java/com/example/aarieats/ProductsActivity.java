package com.example.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aarieats.http.HttpListner;
import com.example.aarieats.http.ProductListner;
import com.example.aarieats.http.api.AariEatsApi;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.Products;
import com.example.aarieats.models.singletons.UserInfo;
import com.example.aarieats.models.viewmodels.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    ListView mProductListView;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        mProductListView = findViewById(R.id.productList);
        ProductsViewModel productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.getProducts().observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(@Nullable List<Products> products) {
                mAdapter =  new ArrayAdapter<String>(ProductsActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1,getProductList(products));
                mProductListView.setAdapter(mAdapter);
            }
        });
    }

    private List<String> getProductList(List<Products> productsList) {
        List<String> productList = new ArrayList<>();
        for(Products products : productsList) {
            productList.add(products.getProductName());
        }
        return productList;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
