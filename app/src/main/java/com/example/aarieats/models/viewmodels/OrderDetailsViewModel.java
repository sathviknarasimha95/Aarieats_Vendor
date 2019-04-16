package com.example.aarieats.models.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.aarieats.models.Order;
import com.example.aarieats.models.OrderDetails;

import java.util.List;

public class OrderDetailsViewModel extends ViewModel {

    private MutableLiveData<List<OrderDetails>> mOrderDetails;

    public LiveData<List<OrderDetails>> getOrderDetails() {
        if (mOrderDetails == null) {
            mOrderDetails = new MutableLiveData<List<OrderDetails>>();
        }
        return mOrderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orders) {
        this.mOrderDetails.postValue(orders);
    }
}
