package com.example.jose.smartlapalma.Controllers.Utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class Request {

    private static final String TAG = "Request";

    public static RequestQueue requestQueue;

    private static final String BUS_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/1/query?where=1%3D1&outFields=OBJECTID," +
            "ID,LATITUD,LONGITUD,PARADA,LINEAS&returnGeometry=false&outSR=4326&f=json";

    // Get JSON with bus stops of the island
    public static void getBusStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, BUS_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Convert response to json object
                        try {

                            JSONObject obj = new JSONObject(response);

                            Log.d(TAG, obj.toString());
                            Log.d(TAG, obj.getString("features"));

                        } catch (Throwable t) {
                            Log.e(TAG, "error while parsing the json.");
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
