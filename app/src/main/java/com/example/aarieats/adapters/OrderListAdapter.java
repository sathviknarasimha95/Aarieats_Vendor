package com.example.aarieats.adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aarieats.R;
import com.example.aarieats.VieworderActivity;
import com.example.aarieats.models.Order;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {

    private List<Order> mData;

    private Context mContext;

    private VieworderActivity.ListMethods mListMethods;

    public OrderListAdapter(List<Order> orders, Context context, VieworderActivity.ListMethods listMethods) {
        super(context, R.layout.order_row_item,orders);
        this.mData = orders;
        this.mContext = context;
        this.mListMethods = listMethods;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_row_item, parent, false);
        }

        TextView orderId = convertView.findViewById(R.id.orderIdValue);
        TextView userEmail = convertView.findViewById(R.id.userEmailValue);
        TextView orderStatus = convertView.findViewById(R.id.userOrderStatus);
        TextView payment = convertView.findViewById(R.id.orderPaymentType);

        final Order order = mData.get(position);

        if(order!=null) {
            orderId.setText(order.getOrderId());
            userEmail.setText(order.getUserEmail());
            orderStatus.setText(order.getOrderStatus());
            payment.setText(order.getPaymentType());
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogToUpdate(order);
            }
        });
        return convertView;
    }

    private void showDialogToUpdate(Order order) {
        mListMethods.updateOrder(order);
    }
}
