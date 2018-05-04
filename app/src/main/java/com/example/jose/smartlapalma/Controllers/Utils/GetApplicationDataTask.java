package com.example.jose.smartlapalma.Controllers.Utils;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Views.Activities.MainActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetApplicationDataTask extends AsyncTask<Void, Integer, Boolean> {

    private static final String TAG = "GetApplicationDataTask";

    private Context mContext;

    private OpenDataLaPalma mOpenDataLaPalma;

    private volatile static boolean busStopCallState;
    private volatile static boolean taxiStopCallState;
    private volatile static boolean touristAccommodationCallState;
    private volatile static boolean churchCallState;
    private volatile static boolean archeologicalSiteCallState;
    private volatile static boolean libraryCallState;
    private volatile static boolean monumentCallState;
    private volatile static boolean weatherCallState;

    public GetApplicationDataTask(Context context, OpenDataLaPalma openDataLaPalma){

        mContext = context;
        mOpenDataLaPalma = openDataLaPalma;

        busStopCallState = true;
        taxiStopCallState = false;
        touristAccommodationCallState = true;
        churchCallState = true;
        archeologicalSiteCallState = true;
        libraryCallState = true;
        monumentCallState = true;
        weatherCallState = true;
    }

    @Override
    protected Boolean doInBackground(Void... context) {

        Request.requestQueue = Volley.newRequestQueue(mContext);

        Request.getBusStops();
        Request.getTaxiStops();
        Request.getTouristAccommodation();
        Request.getChurchs();
        Request.getArcheologicalSites();
        Request.getLibraries();
        Request.getMonuments();
        Request.getWeatherInfo();

        // Wait for resuts of calls
        while(!busStopCallState && !taxiStopCallState && !touristAccommodationCallState
                && !churchCallState && !archeologicalSiteCallState && !libraryCallState
                && !monumentCallState && !weatherCallState){
            try {  Thread.sleep(250); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }

        // Get lat/lng to taxi stops from geocoder
        if(Geocoder.isPresent()){

            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            Log.d(TAG, String.valueOf(mOpenDataLaPalma.getmTaxiStopList().size()));

            for (int i = 0; i < mOpenDataLaPalma.getmTaxiStopList().size(); i++) {

                try {
                    // Try to get lat/lng from a direction
                    List<Address> addresses = geocoder.getFromLocationName(
                            mOpenDataLaPalma.getmTaxiStopList().get(i).getmDirection(), 1);

                    if(!addresses.isEmpty()){
                        // Save lat/lng
                        mOpenDataLaPalma.getmTaxiStopList().get(i).setmLat(
                                addresses.get(0).getLatitude());
                        mOpenDataLaPalma.getmTaxiStopList().get(i).setmLng(
                                addresses.get(0).getLongitude());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {

        if(result){

            // Go to main activity
            mContext.startActivity(new Intent(mContext, MainActivity.class));
        }
    }

    public static void setBusStopCallState(boolean busStopCallState) {
        GetApplicationDataTask.busStopCallState = busStopCallState;
    }

    public static void setTaxiStopCallState(boolean taxiStopCallState) {
        GetApplicationDataTask.taxiStopCallState = taxiStopCallState;
    }

    public static boolean isBusStopCallState() {
        return busStopCallState;
    }

    public static boolean isTaxiStopCallState() {
        return taxiStopCallState;
    }

    public static boolean isTouristAccommodationCallState() {
        return touristAccommodationCallState;
    }

    public static void setTouristAccommodationCallState(boolean touristAccommodationCallState) {
        GetApplicationDataTask.touristAccommodationCallState = touristAccommodationCallState;
    }

    public static boolean isChurchCallState() {
        return churchCallState;
    }

    public static void setChurchCallState(boolean churchCallState) {
        GetApplicationDataTask.churchCallState = churchCallState;
    }

    public static boolean isArcheologicalSiteCallState() {
        return archeologicalSiteCallState;
    }

    public static void setArcheologicalSiteCallState(boolean archeologicalSiteCallState) {
        GetApplicationDataTask.archeologicalSiteCallState = archeologicalSiteCallState;
    }

    public static boolean isLibraryCallState() {
        return libraryCallState;
    }

    public static void setLibraryCallState(boolean libraryCallState) {
        GetApplicationDataTask.libraryCallState = libraryCallState;
    }

    public static boolean isMonumentCallState() {
        return monumentCallState;
    }

    public static void setMonumentCallState(boolean monumentCallState) {
        GetApplicationDataTask.monumentCallState = monumentCallState;
    }

    public static boolean isWeatherCallState() {
        return weatherCallState;
    }

    public static void setWeatherCallState(boolean weatherCallState) {
        GetApplicationDataTask.weatherCallState = weatherCallState;
    }
}
