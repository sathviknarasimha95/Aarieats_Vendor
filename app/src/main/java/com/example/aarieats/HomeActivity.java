package com.example.aarieats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aarieats.models.singletons.UserInfo;
import com.onesignal.OneSignal;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout addProductLayout;

    private LinearLayout viewOrderLayout;

    private LinearLayout getProductLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addProductLayout = findViewById(R.id.addProductLayout);
        getProductLayout = findViewById(R.id.productLayout);
        viewOrderLayout = findViewById(R.id.viewOrderLayout);
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

        OneSignal.setEmail(UserInfo.getInstance().getVendorInfo().getEmail());
        OneSignal.sendTag("email",UserInfo.getInstance().getVendorInfo().getEmail());
        OneSignal.sendTag("UserType","Vendor");
    }
}
