package com.user.jose.smartlapalma.Controllers.Utils;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.user.jose.smartlapalma.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

public class UserLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "UserLocation";

    private Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Location mLocation;

    public UserLocation(Context ctx) {

        mContext = ctx;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // If user not has permissions granted, return
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Get current location
        FusedLocationProviderClient mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(mContext);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity) mContext, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location
                        if (location != null) {
                            mLocation = location;
                        }
                    }
                });

        if(mLocation != null) {

            // Add user location to the map
            LatLng userLocation = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLng(userLocation));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        // Show toast error
        Toast.makeText(mContext,
                mContext.getString(R.string.gps_conection_error),
                Toast.LENGTH_SHORT).show();
    }

    public Context getmContext() {
        return mContext;
    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public Location getmLocation() {
        return mLocation;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }
}
