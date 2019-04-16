package com.example.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aarieats.adapters.OrderListAdapter;
import com.example.aarieats.http.GetOrderListner;
import com.example.aarieats.http.api.AariEatsApi;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.Order;
import com.example.aarieats.models.viewmodels.ViewOrdersViewModel;

import java.util.List;

public class VieworderActivity extends AppCompatActivity {

    private ListView mOrderListView;
    private OrderListAdapter mOrderListAdapter;
    private ViewOrdersViewModel mViewOrdersViewModel;

    private AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworder);
        mOrderListView = findViewById(R.id.orderList);
        mViewOrdersViewModel = ViewModelProviders.of(this).get(ViewOrdersViewModel.class);
        mViewOrdersViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                mOrderListAdapter = new OrderListAdapter(orders,VieworderActivity.this,mListMethods);
                mOrderListView.setAdapter(mOrderListAdapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getOrderFromServer();
    }

    public interface ListMethods {
        void updateOrder(Order order);
    }

    public ListMethods mListMethods = new ListMethods() {
        @Override
        public void updateOrder(Order order) {
            int status = 0;
            if(order.getOrderStatus().equals("pending")) {
                status = 4;
            } else if(order.getOrderStatus().equals("accepted")) {
                status = 5;
            } else if(order.getOrderStatus().equals("outofdelivery")) {
                status = 6;
            }
            startProductDetailActivity(order,status);
        }
    };

    private void startProductDetailActivity(Order order,int status) {
        Intent intent = new Intent(VieworderActivity.this,OrderDetailsActivity.class);
        intent.putExtra("orderId",order.getOrderId());
        intent.putExtra("orderStatus",status);
        startActivity(intent);
    }



    private void getOrderFromServer() {
        ApiService.getInstance().getOrders(new GetOrderListner() {
            @Override
            public void onSuccess(ResponseStatus status, List<Order> info) {
                if(info!=null) {
                    mViewOrdersViewModel.setOrders(info);
                } else {
                    Toast.makeText(VieworderActivity.this,"Internal Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(VieworderActivity.this,info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
