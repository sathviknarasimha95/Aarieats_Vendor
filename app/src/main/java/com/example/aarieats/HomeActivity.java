package com.example.aarieats;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aarieats.models.MainMenuItems;
import com.example.aarieats.models.singletons.UserInfo;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ItemOnClickListener{

    private LinearLayout addProductLayout;

    private LinearLayout viewOrderLayout;

    private LinearLayout getProductLayout;

    private LinearLayout orderHistoryLayout;

    private LinearLayout paymentHistoryLayout;

    List<MainMenuItems> data;

    Adapter mAdapter;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addProductLayout = findViewById(R.id.addProductLayout);
        getProductLayout = findViewById(R.id.productLayout);
        viewOrderLayout = findViewById(R.id.viewOrderLayout);
        orderHistoryLayout = findViewById(R.id.orderHistoryLayout);
        paymentHistoryLayout = findViewById(R.id.orderPaymentsLayout);
        addProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddprouctsActivity.class));
            }
        });

        getProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ProductsActivity.class));
            }
        });

        viewOrderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,VieworderActivity.class));
            }
        });
        orderHistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,OrderHistory.class));
            }
        });

        paymentHistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PaymentHistory.class));
            }
        });
//        mRecyclerView = (RecyclerView) findViewById(R.id.gridViewLayout);
//        mAdapter = new Adapter(data,this,this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        mRecyclerView.setAdapter(mAdapter);
        OneSignal.setEmail(UserInfo.getInstance().getVendorInfo().getEmail());
        OneSignal.sendTag("email",UserInfo.getInstance().getVendorInfo().getEmail().toLowerCase());
        OneSignal.sendTag("UserType","Vendor");
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeActivity.this);
        alertDialog.setTitle("Exit");

        alertDialog.setMessage("are you sure?");
        alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onItemClick(int position, MainMenuItems data, View imgView) {
        String action = data.getMenuName();
        switch (action) {
            case "add_products" : startActivity(new Intent(HomeActivity.this,AddprouctsActivity.class));
                    break;
            case "products" : startActivity(new Intent(HomeActivity.this,ProductsActivity.class));
                    break;
            case "orders" : startActivity(new Intent(HomeActivity.this,VieworderActivity.class));
                    break;
        }
    }
}
