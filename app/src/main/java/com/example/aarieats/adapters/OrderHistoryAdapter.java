package com.example.aarieats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aarieats.R;
import com.example.aarieats.VieworderActivity;
import com.example.aarieats.models.Order;

import java.util.List;

public class OrderHistoryAdapter extends ArrayAdapter<Order> {

    private List<Order> mData;

    private Context mContext;


    public OrderHistoryAdapter(List<Order> orders, Context context) {
        super(context, R.layout.order_row_item,orders);
        this.mData = orders;
        this.mContext = context;
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

        return convertView;
    }
}
