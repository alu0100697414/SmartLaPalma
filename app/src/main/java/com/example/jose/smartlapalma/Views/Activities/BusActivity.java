package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Controllers.Utils.Request;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class BusActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = "BusActivity";

    private SharedPreferences mPrefs;

    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

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

    public static void loadData(){
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();
        Log.d(TAG, String.valueOf(openDataLaPalma.getmBusStopList().size()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Configuration
        Marker currentMarker;

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(28.663663, -17.856308), 10));

        // Load bus stops in map
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        for(int i=0; i<openDataLaPalma.getmBusStopList().size(); i++){
            currentMarker = googleMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .title(openDataLaPalma.getmBusStopList().get(i).getmName())
                    .anchor(0.0f, 1.0f)
                    .position(new LatLng(openDataLaPalma.getmBusStopList().get(i).getmLat(),
                                         openDataLaPalma.getmBusStopList().get(i).getmLng())));

            currentMarker.setTag(openDataLaPalma.getmBusStopList().get(i).getmId());

            googleMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Retrieve the data from the marker.
        Integer tag = (Integer) marker.getTag();

        // Get lines from the maker
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        String lines = "";
        for(int i=0; i<openDataLaPalma.getmBusStopList().size(); i++){

            if(openDataLaPalma.getmBusStopList().get(i).getmId() == tag){
                lines = getString(R.string.snipped_intro) + " " +
                        openDataLaPalma.getmBusStopList().get(i).getmLine();
            }
        }

        // Show toast with the lines
        if(!lines.isEmpty()){
            Toast.makeText(this, lines, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), TransportsActivity.class));
    }
}
