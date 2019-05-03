package com.example.aarieats;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by sathvik on 1/12/2018.
 */
//this whole class is used to get the data from the json object
public class DataParser {
    private HashMap<String,String> getDuration(JSONArray googleDirectonsJson)
    {
        HashMap<String,String> googleDirectionsMap = new HashMap<>();
        String duration = "";
        String distance = "";

        Log.d("json response",googleDirectonsJson.toString());
        try {
            duration = googleDirectonsJson.getJSONObject(0).getJSONObject("duration").getString("text");
            distance = googleDirectonsJson.getJSONObject(0).getJSONObject("distance").getString("text");

            googleDirectionsMap.put("duration",duration);
            googleDirectionsMap.put("distance",distance);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googleDirectionsMap;
    }

    public String  parseAddress(String addressjson)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        String currentAddress = "";

        try {
            jsonObject = new JSONObject(addressjson);
            currentAddress = jsonObject.getJSONArray("results").getJSONObject(0).getString("formatted_address");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return currentAddress;
    }
    public String parsePlaceId(String addressjson)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        String placeId = "";

        try {
            jsonObject = new JSONObject(addressjson);
            placeId = jsonObject.getJSONArray("results").getJSONObject(0).getString("place_id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return placeId;
    }

    public LatLngBounds getBounds(String jsonData)
    {

        JSONObject jsonBoundsNorth = null;
        JSONObject jsonBoundsSouth = null;
        JSONObject jsonObject;
        LatLngBounds bounds = null;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonBoundsNorth = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("bounds").getJSONObject("northeast");
            jsonBoundsSouth = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONObject("bounds").getJSONObject("southwest");
            Log.i("sonlength",jsonBoundsNorth.get("lat").toString());

            bounds = converToLatLangBounds(jsonBoundsNorth,jsonBoundsSouth);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bounds;
    }

    public LatLngBounds converToLatLangBounds(JSONObject north, JSONObject south)
    {
        LatLng northeast = null;
        LatLng southwest = null;

        try {
            northeast = new LatLng(north.getDouble("lat"),north.getDouble("lng"));
            southwest = new LatLng(south.getDouble("lat"),south.getDouble("lng"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return(new LatLngBounds(southwest,northeast));
    }

    public String[] parseDirections(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            Log.i("jsonData",jsonObject+"");
            jsonArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPaths(jsonArray);
    }
    public String[] getPaths(JSONArray googleStepsJson)
    {
        int count = googleStepsJson.length();
        String[] polylines = new String[count];
        for (int i = 0; i < count; i++) {
            try {
                polylines[i] = getPath(googleStepsJson.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return polylines;
    }

    public String getPath(JSONObject googlePathJson)
    {
        String polyline ="";
        try {
            polyline = googlePathJson.getJSONObject("polyline").getString("points");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return polyline;
    }
}

