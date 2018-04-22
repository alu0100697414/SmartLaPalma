package com.example.jose.smartlapalma.Controllers.Utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.jose.smartlapalma.Models.BusStop;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Views.Activities.BusActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Request {

    private static final String TAG = "Request";

    public static RequestQueue requestQueue;

    private static final String BUS_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/1/query?where=1%3D1&outFields=ID," +
            "LATITUD,LONGITUD,PARADA,LINEAS&returnGeometry=false&outSR=4326&f=json";

    // Get JSON with bus stops of the island
    public static void getBusStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, BUS_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(BusStop.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                openDataLaPalma.getmBusStopList().add(new BusStop(
                                        currentObject.getJSONObject(BusStop.mAttributesKey).getInt(BusStop.mIdKey),
                                        currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLatKey),
                                        currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLngKey),
                                        currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mNameKey),
                                        currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLineKey)
                                ));
                            }

                            BusActivity.loadData();
                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });

        requestQueue.add(stringRequest);
    }
}
