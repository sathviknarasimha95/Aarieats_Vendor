package com.example.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aarieats.adapters.OrderHistoryAdapter;
import com.example.aarieats.http.GetOrderListner;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.Order;
import com.example.aarieats.models.viewmodels.ViewOrdersViewModel;

import java.util.List;

public class PaymentHistory extends AppCompatActivity {

    private ListView mOrderListView;
    private OrderHistoryAdapter mOrderListAdapter;
    private ViewOrdersViewModel mViewOrdersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        mOrderListView = findViewById(R.id.paymentHistoryList);
        mViewOrdersViewModel = ViewModelProviders.of(this).get(ViewOrdersViewModel.class);
        mViewOrdersViewModel.getOrderHistory().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                mOrderListAdapter = new OrderHistoryAdapter(orders,PaymentHistory.this);
                mOrderListView.setAdapter(mOrderListAdapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getOrderFromServer();
    }

    private void getOrderFromServer() {
        ApiService.getInstance().getPaymentHistory(new GetOrderListner() {
            @Override
            public void onSuccess(ResponseStatus status, List<Order> info) {
                if(info!=null) {
                    mViewOrdersViewModel.setOrderHistory(info);
                } else {
                    Toast.makeText(PaymentHistory.this,"Internal Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(PaymentHistory.this,info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
