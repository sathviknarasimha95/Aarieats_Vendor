package com.example.aarieats.models.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.aarieats.models.Order;
import com.example.aarieats.models.Products;

import java.util.List;

public class ViewOrdersViewModel extends ViewModel {

    private MutableLiveData<List<Order>> mOrders;

    private MutableLiveData<List<Order>> mOrderHistory;

    private MutableLiveData<List<Order>> mPaymentHistory;

    public LiveData<List<Order>> getOrders() {
        if (mOrders == null) {
            mOrders = new MutableLiveData<List<Order>>();
        }
        return mOrders;
    }

    public LiveData<List<Order>> getOrderHistory() {
        if (mOrderHistory == null) {
            mOrderHistory = new MutableLiveData<List<Order>>();
        }
        return mOrderHistory;
    }

    public LiveData<List<Order>> getPaymentHistory() {
        if (mPaymentHistory == null) {
            mPaymentHistory = new MutableLiveData<List<Order>>();
        }
        return mPaymentHistory;
    }

    public void setOrders(List<Order> orders) {

        this.mOrders.postValue(orders);
    }

    public void setOrderHistory(List<Order> orders) {
        this.mOrderHistory.postValue(orders);
    }

    public void setmPaymentHistory(List<Order> orders) {
        this.mPaymentHistory.postValue(orders);
    }

}
