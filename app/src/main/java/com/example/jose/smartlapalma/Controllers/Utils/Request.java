package com.example.jose.smartlapalma.Controllers.Utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jose.smartlapalma.Models.BusStop;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Models.TaxiStop;
import com.example.jose.smartlapalma.Views.Activities.BusActivity;
import com.example.jose.smartlapalma.Views.Activities.TaxiActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

    private static final String TAG = "Request";

    public static RequestQueue requestQueue;

    private static final String BUS_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/1/query?where=1%3D1&outFields=ID," +
            "PARADA,DMSLat,DMSLon,LINEAS&returnGeometry=false&outSR=4326&f=json";

    private static final String TAXI_STOPS_URL = "https://services.arcgis.com/hkQNLKNeDVYBjvFE/" +
            "arcgis/rest/services/Transportes/FeatureServer/0/query?where=1%3D1&outFields=Id," +
            "Municipio,Telefono,Direccion,Nombre&returnGeometry=false&outSR=4326&f=json";

    // Get JSON with bus stops of the island
    public static void getBusStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, BUS_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmBusStopList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(BusStop.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String latitude = currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLatKey);
                                String longitude = currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLngKey);

                                if(!latitude.isEmpty() && !longitude.isEmpty()){

                                    // Get latitude and longitude in double
                                    double customLat = CustomUtils.getLat(latitude);
                                    double customLng = CustomUtils.getLng(longitude);

                                    // Save the stop
                                    openDataLaPalma.getmBusStopList().add(new BusStop(
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getInt(BusStop.mIdKey),
                                            customLat,
                                            customLng,
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mNameKey),
                                            currentObject.getJSONObject(BusStop.mAttributesKey).getString(BusStop.mLineKey)
                                    ));
                                }
                            }

                            BusActivity.loadData();
                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json.");
                        }

                        GetApplicationDataTask.setBusStopCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setBusStopCallState(true);
                    }
        });

        requestQueue.add(stringRequest);
    }

    // Get JSON with taxi stops of the island
    public static void getTaxiStops(){

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, TAXI_STOPS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Get instance of singleton class and clear previuos data
                        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
                        openDataLaPalma.getmTaxiStopList().clear();

                        try {
                            // Convert string to JSON and get item json array
                            JSONObject obj = new JSONObject(response);
                            JSONArray jArray = obj.getJSONArray(TaxiStop.mFeaturesKey);

                            // Save items in singleton
                            for (int i=0; i<jArray.length(); i++) {

                                JSONObject currentObject = jArray.getJSONObject(i);

                                String direction = currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mDireccionKey)
                                        + ", " + currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mMunicipioKey);

                                // Save the stop
                                openDataLaPalma.getmTaxiStopList().add(new TaxiStop(
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getInt(TaxiStop.mIdKey),
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mNombreKey),
                                        direction,
                                        currentObject.getJSONObject(TaxiStop.mAttributesKey).getString(TaxiStop.mTelefonoKey)
                                ));

                            }

                            TaxiActivity.loadData();
                        } catch (Throwable t) {
                            Log.e(TAG, "Error while parsing the json.");
                        }

                        GetApplicationDataTask.setTaxiStopCallState(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        GetApplicationDataTask.setTaxiStopCallState(true);
                    }
                });

        requestQueue.add(stringRequest);
    }
}
