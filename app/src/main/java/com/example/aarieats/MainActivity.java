package com.example.aarieats;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,RegisterationFragment.OnFragmentInteractionListener,MapRegFragment.OnFragmentInteractionListener{
    public static final int PERMISSION_REQUEST_LOCATION_CODE = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        createOneSignal();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void createOneSignal() {
    }

    @Override
    public void goToHome() {
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }

    @Override
    public void sendData(String name) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        if(fragment instanceof MapRegFragment) {
            Log.i("fragment","true");
        }
    }

    public boolean checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
}
