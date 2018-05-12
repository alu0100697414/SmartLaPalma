package com.user.jose.smartlapalma.Views.Activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.user.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.user.jose.smartlapalma.Controllers.Utils.UserLocation;
import com.user.jose.smartlapalma.Models.OpenDataLaPalma;
import com.user.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.user.jose.smartlapalma.Models.Transports.TaxiStop;
import com.user.jose.smartlapalma.Models.UserType;
import com.user.jose.smartlapalma.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TaxiActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = "TaxiActivity";

    private final int REQUEST_PERMISSION_LOCATION = 101;
    private final int RESULT_GPS_CODE = 102;

    private GoogleMap mMap;
    private SharedPreferences mPrefs;

    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        // Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Shared preferences
        mPrefs = getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        typeUser = mPrefs.getInt(SharedPreferencesKeys.USER_TYPE, UserType.RESIDENT_USER);

        // Init and configure Material Drawer
        CustomMaterialDrawer customMaterialDrawer = new CustomMaterialDrawer(this);

        if(typeUser == UserType.RESIDENT_USER){
            customMaterialDrawer.setResidentMode();
        } else {
            customMaterialDrawer.setTouristMode();
        }

        // Init Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Configuration
        mMap = googleMap;

        Marker currentMarker;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(28.663663, -17.856308), 10));

        // Set options and controls
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        // Load bus stops in map
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        if(openDataLaPalma.getmTaxiStopList().size() > 0){

            // Put markers in map
            for (int i = 0; i < openDataLaPalma.getmTaxiStopList().size(); i++) {

                if(openDataLaPalma.getmTaxiStopList().get(i).getmLat() != TaxiStop.DEFAULT_COORDINATES_VALUE
                        && openDataLaPalma.getmTaxiStopList().get(i).getmLng() != TaxiStop.DEFAULT_COORDINATES_VALUE){

                    currentMarker = googleMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                            .title(openDataLaPalma.getmTaxiStopList().get(i).getmName())
                            .anchor(0.0f, 1.0f)
                            .position(new LatLng(openDataLaPalma.getmTaxiStopList().get(i).getmLat(),
                                    openDataLaPalma.getmTaxiStopList().get(i).getmLng())));

                    currentMarker.setTag(openDataLaPalma.getmTaxiStopList().get(i).getmId());
                }
            }

            googleMap.setOnMarkerClickListener(this);
        } else {
            // Show error message
            Toast.makeText(this, getString(R.string.items_not_found), Toast.LENGTH_SHORT).show();
        }

        // Set user location
        getUserLocation();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Retrieve the data from the marker.
        Integer tag = (Integer) marker.getTag();

        // Get phones from the maker
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        String phones = "";
        for(int i=0; i<openDataLaPalma.getmTaxiStopList().size(); i++){

            if(openDataLaPalma.getmTaxiStopList().get(i).getmId() == tag &&
                    !openDataLaPalma.getmTaxiStopList().get(i).getmPhone().equals("0") &&
                    !openDataLaPalma.getmTaxiStopList().get(i).getmPhone().isEmpty()){
                phones = getString(R.string.taxi_toast) + " " +
                        openDataLaPalma.getmTaxiStopList().get(i).getmPhone();
            }
        }

        // If phone is empty, show phone unavailable message
        if(phones.isEmpty()){
            phones = getString(R.string.phone_not_available);
        }

        // Show toast with the phone number
        if(!phones.isEmpty()){
            Toast.makeText(this, phones, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public static void loadData() {
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
        Log.d(TAG, String.valueOf(openDataLaPalma.getmTaxiStopList().size()));
    }

    private void getUserLocation(){

        // If user not has permission granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_LOCATION);
        } else {

            // Check is GPS is turned on
            final LocationManager locationManager = (LocationManager)
                    getSystemService( Context.LOCATION_SERVICE );

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertMessageNoGps();
            } else {
                // Set user location
                mMap.setMyLocationEnabled(true);

                UserLocation userLocation = new UserLocation(this);
                userLocation.setmMap(mMap);
                userLocation.getmGoogleApiClient().connect();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_LOCATION: {
                // If request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Get user position
                    getUserLocation();
                } else {
                    // Show toast of GPS unavailable
                    Toast.makeText(this, getString(R.string.gps_not_available), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == RESULT_GPS_CODE && resultCode == 0){

            final LocationManager locationManager = (LocationManager)
                    getSystemService( Context.LOCATION_SERVICE );

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                // Show toast of GPS unavailable
                Toast.makeText(getApplicationContext(),
                        getString(R.string.gps_not_available),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Get user position
                getUserLocation();
            }
        }
    }

    private void buildAlertMessageNoGps() {

        // Create alert dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.text_gps_dialog))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), RESULT_GPS_CODE);
                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                        dialog.cancel();

                        // Show toast of GPS unavailable
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.gps_not_available),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        final AlertDialog alert = builder.create();

        // Show alert dialog
        alert.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), TransportsActivity.class));
    }
}
