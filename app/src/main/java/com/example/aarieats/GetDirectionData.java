package com.example.aarieats;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

/**
 * Created by sathvik on 1/12/2018.
 */

public class GetDirectionData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionDate;
    String duration,distance;
    LatLng endlatlang;
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        endlatlang = (LatLng)objects[2];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionDate = downloadUrl.readUrl(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionDate;
    }

    @Override
    protected void onPostExecute(String s) {

        String[] directionList;
        DataParser parser = new DataParser();
        directionList = parser.parseDirections(s);
        displayDirection(directionList);
        LatLngBounds bounds;
        DataParser boundParser = new DataParser();
        bounds = parser.getBounds(s);
        CameraUpdate focusPath = CameraUpdateFactory.newLatLngBounds(bounds,160);
        mMap.animateCamera(focusPath);

    }

    public void displayDirection(String[] directionList)
    {

        int count = directionList.length;
        for(int i = 0 ; i < count ; i++)
        {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.BLUE);
            options.width(10);
            options.addAll(PolyUtil.decode(directionList[i]));
            mMap.addPolyline(options);
        }
    }
}
