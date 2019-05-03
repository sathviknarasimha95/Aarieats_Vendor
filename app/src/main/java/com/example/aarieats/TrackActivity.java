package com.example.aarieats;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.aarieats.models.singletons.UserInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TrackActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    MapView mMapView;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private Marker currentLocationMarker;
    private SupportMapFragment mapFragment;
    private Object[] dataTransfer;
    private Bundle extras;
    private Marker toLoc;
    private Marker fromLoc;
    private static boolean isMapShown = false;
    public static final int PERMISSION_REQUEST_LOCATION_CODE = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
//        mMapView = findViewById(R.id.mapTrack);
//        //mMapView.onCreate(savedInstanceState);
//        mMapView.onResume(); // needed to get the map to display immediately
//        try {
//            MapsInitializer.initialize(getApplicationContext());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mMapView.getMapAsync(this);
        extras = getIntent().getExtras();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //mMapView.getMapAsync(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if(currentLocationMarker!=null) {
            currentLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationMarker = mMap.addMarker(markerOptions);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        if(!isMapShown) {
            mMap.animateCamera(cameraUpdate);
            isMapShown = true;
        }
        //mMap.animateCamera(CameraUpdateFactory.zoomBy(5));
//        if(client!=null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
        if (extras.getString("userLocationLatLng") !=null) {
            //Toast.makeText(getApplicationContext(),"now you can extract the direction",Toast.LENGTH_SHORT).show();
            markSourceAndDestination();
            getDirectionData();
        }

    }

    protected void getDirectionData() {
        dataTransfer = new Object[3];
        String userLocation = extras.getString("userLocationLatLng");
        String vendorLocation = UserInfo.getInstance().getVendorInfo().getLatLng();
        String startLat = vendorLocation.split(":")[0];
        String startLng = vendorLocation.split(":")[1];
        String endLat = userLocation.split(":")[0];
        String endLng = userLocation.split(":")[1];
        String url = getDirectionsUrl(startLat ,startLng, endLat, endLng);
        GetDirectionData getDirectionData = new GetDirectionData();
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;
        dataTransfer[2] = new LatLng(Double.parseDouble(endLat), Double.parseDouble(endLng));
        getDirectionData.execute(dataTransfer);
    }

    private void markSourceAndDestination() {
        if (toLoc != null && fromLoc != null) {
            toLoc.remove();
            fromLoc.remove();
        }
        String userLocation = extras.getString("userLocationLatLng");
        String vendorLocation = UserInfo.getInstance().getVendorInfo().getLatLng();
        String startLat = vendorLocation.split(":")[0];
        String startLng = vendorLocation.split(":")[1];
        String endLat = userLocation.split(":")[0];
        String endLng = userLocation.split(":")[1];
        LatLng startLatLng = new LatLng(Double.valueOf(startLat),Double.valueOf(startLng));
        LatLng endLatLng = new LatLng(Double.valueOf(endLat),Double.valueOf(endLng));
        MarkerOptions markerFromLocOpt = new MarkerOptions();
        markerFromLocOpt.position(startLatLng);
        markerFromLocOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        toLoc = mMap.addMarker(markerFromLocOpt);

        MarkerOptions markerToLocOpt = new MarkerOptions();
        markerToLocOpt.position(endLatLng);
        markerToLocOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        fromLoc = mMap.addMarker(markerToLocOpt);

    }

    private String getDirectionsUrl(String startlat, String startlng, String endlat, String endlng) {
        StringBuilder googleDirectionUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionUrl.append("origin=" + startlat + "," + startlng);
        googleDirectionUrl.append("&destination=" + endlat + "," + endlng);
        googleDirectionUrl.append("&key=" + "AIzaSyC6IqvqLsIL4aJkMtaQkHvmwsRYI1VtrjU");
        Log.i("googleurl", googleDirectionUrl.toString());
        return googleDirectionUrl.toString();
    }

    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION_CODE :
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_DENIED) {
                        if(client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    } else {
                        Toast.makeText(this,"Permission Denaied",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
        }
    }
}
