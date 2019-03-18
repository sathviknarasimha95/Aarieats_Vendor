package com.example.aarieats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aarieats.http.AddProductListner;
import com.example.aarieats.http.api.ApiService;
import com.example.aarieats.models.singletons.UserInfo;

import java.util.Random;

public class AddprouctsActivity extends AppCompatActivity {

    private Button mCancelBtn;

    private Button mAddProductBtn;

    private EditText mProductName;

    private EditText mProductType;

    private EditText mProductPrice;

    private EditText mProductUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproucts);

        mProductName = findViewById(R.id.productName);
        mProductType = findViewById(R.id.productType);
        mProductPrice = findViewById(R.id.productPrice);
        mProductUnit = findViewById(R.id.productUnit);

        mCancelBtn = findViewById(R.id.cancel);

        mAddProductBtn = findViewById(R.id.addProduct);

        mAddProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToServer();
            }
        });
    }

    private void addProductToServer() {

        if(mProductName.getText().toString().length() > 0 && mProductType.getText().toString().length() > 0 && mProductPrice.getText().toString().length() > 0 && mProductUnit.getText().toString().length() > 0) {
            ApiService apiService = ApiService.getInstance();
            apiService.addProducts(generateProductId(), mProductName.getText().toString(), mProductType.getText().toString(), mProductPrice.getText().toString(), mProductUnit.getText().toString(), new AddProductListner() {
                @Override
                public void onSuccess(ResponseStatus status, String info) {
                    if(status == ResponseStatus.SUCCESS) {
                        Toast.makeText(AddprouctsActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddprouctsActivity.this,"Invalid Parameters",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(ResponseStatus status, String info) {
                    Toast.makeText(AddprouctsActivity.this,info,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private String generateProductId() {
        String email = UserInfo.getInstance().getVendorInfo().getEmail();

        String chars = email.substring(0,4);
        Random random = new Random();
        String id = chars+random.nextInt(99);
        return id;
    }
}
