package com.example.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aarieats.http.GetOrderDetailsListner;
import com.example.aarieats.http.UpdateOrderListner;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.OrderDetails;
import com.example.aarieats.models.singletons.UserInfo;
import com.example.aarieats.models.viewmodels.OrderDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    private Bundle mExtras;

    private String mOrderId;

    private int mOrderStatus = 0;

    private ListView mOrderDetailsListView;

    private OrderDetailsViewModel mOrderDetailsViewModel;

    private ArrayAdapter<String> mAdapter;

    private Button mUpdateBtn;

    private Button mDeclineBtn;

    private Button mTrackBtn;

    private String mUserLatLng;

    private TextView mTotalText;

    private AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mExtras = getIntent().getExtras();
        if(mExtras!=null) {
            mOrderId = mExtras.getString("orderId");
            mOrderStatus = mExtras.getInt("orderStatus");
            mUserLatLng = mExtras.getString("userLatLng");
        }
        mUpdateBtn = findViewById(R.id.updateBtn);
        mDeclineBtn = findViewById(R.id.declineBtn);
        mTrackBtn = findViewById(R.id.trackBtn);
        mTotalText = findViewById(R.id.priceText);

        if(mOrderStatus!=4) {
            mDeclineBtn.setVisibility(View.GONE);
        }
        if(mOrderStatus == 0) {
            mUpdateBtn.setVisibility(View.GONE);
            mDeclineBtn.setVisibility(View.GONE);
        }

        if(mUserLatLng==null) {
            mTrackBtn.setVisibility(View.GONE);
        }
        handleBtns();
        mOrderDetailsListView = findViewById(R.id.orderDetailsListView);
        mOrderDetailsViewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel.class);
        mOrderDetailsViewModel.getOrderDetails().observe(this, new Observer<List<OrderDetails>>() {
            @Override
            public void onChanged(@Nullable List<OrderDetails> orderDetails) {
                mAdapter = new ArrayAdapter<String>(OrderDetailsActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1,getOrderDetailsList(orderDetails));
                mOrderDetailsListView.setAdapter(mAdapter);
                displayTotal(orderDetails);
            }
        });
        mTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsActivity.this,TrackActivity.class);
                intent.putExtra("userLocationLatLng",mUserLatLng);
                startActivity(intent);
            }
        });
    }

    private void displayTotal(List<OrderDetails> orderDetails) {
        int total = 0;
        for(OrderDetails orderDetail : orderDetails) {
            total = total + orderDetail.getProductPrice();
        }
        mTotalText.setText("Total :"+total);
    }

    private void handleBtns() {
        if(mOrderStatus == 4) {
            mUpdateBtn.setText("Accept Order");
        } else if(mOrderStatus == 5) {
            mUpdateBtn.setText("Ready To Deliver");
        } else if(mOrderStatus == 6) {
            mUpdateBtn.setText("Complete Order");
        }
       mUpdateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String message = null;
               if(mOrderId!=null && mOrderStatus!=0) {
                   if(mOrderStatus == 4) {
                       message = "are you sure to accept the order";
                       mUpdateBtn.setText("Accept Order");
                   } else if(mOrderStatus == 5) {
                       message = "order is ready to delivery ?";
                       mUpdateBtn.setText("Ready To Deliver");
                   } else if(mOrderStatus == 6) {
                       message = "complete the order";
                       mUpdateBtn.setText("Complete Order");
                   }
                   showOrderUpdateDialog(message);
               }
           }
       });

       mDeclineBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               declineOrder();
           }
       });
    }

    private void showOrderUpdateDialog(String message) {
        if(message!=null) {
            alertDialog = new AlertDialog.Builder(OrderDetailsActivity.this);
            alertDialog.setTitle("Update Order");
            alertDialog.setMessage(message);
            alertDialog.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    updateOrder();
                }
            });
            alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();
        }
    }

    private void declineOrder() {
        if(mOrderId!=null) {
            ApiService apiService = ApiService.getInstance();
            apiService.updateOrder(mOrderId, 8, new UpdateOrderListner() {
                @Override
                public void onSuccess(ResponseStatus status, String info) {
                    Toast.makeText(OrderDetailsActivity.this,"Order Updated",Toast.LENGTH_SHORT).show();
                    OrderDetailsActivity.super.onBackPressed();
                }

                @Override
                public void onFailure(ResponseStatus status, String info) {
                    Toast.makeText(OrderDetailsActivity.this,"Update Failed",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(OrderDetailsActivity.this,"Internal Error orderId null",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateOrder() {
        int updateStatus = 0;
        if(mOrderStatus == 4) {
            updateStatus = 5;
        } else if(mOrderStatus == 5) {
            updateStatus = 6;
        } else if(mOrderStatus == 6) {
            updateStatus = 7;
        }
        ApiService apiService = ApiService.getInstance();
        if(mOrderId!= null && mOrderStatus != 0) {
            apiService.updateOrder(mOrderId, updateStatus, new UpdateOrderListner() {
                @Override
                public void onSuccess(ResponseStatus status, String info) {
                    Toast.makeText(OrderDetailsActivity.this,"Order Updated",Toast.LENGTH_SHORT).show();
                    OrderDetailsActivity.super.onBackPressed();
                }

                @Override
                public void onFailure(ResponseStatus status, String info) {
                    Toast.makeText(OrderDetailsActivity.this,"Update Failed",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(OrderDetailsActivity.this,"Internal Error orderId null",Toast.LENGTH_SHORT).show();
        }
    }

    private List<String> getOrderDetailsList(List<OrderDetails> orderDetails) {
        List<String> orderDetailList = new ArrayList<>();
        for(OrderDetails orderDetail : orderDetails) {
            orderDetailList.add(orderDetail.getProductName());
        }
        return orderDetailList;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ApiService apiService = ApiService.getInstance();
        apiService.getOrderDetails(mOrderId, UserInfo.getInstance().getVendorInfo().getEmail(), new GetOrderDetailsListner() {
            @Override
            public void onSuccess(ResponseStatus status, List<OrderDetails> info) {
                mOrderDetailsViewModel.setOrderDetails(info);
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(OrderDetailsActivity.this,info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
