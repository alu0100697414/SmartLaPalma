package com.user.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.user.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.user.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.user.jose.smartlapalma.Models.UserType;
import com.user.jose.smartlapalma.R;

public class TransportsActivity extends AppCompatActivity {

    private final String TAG = "TransportsActivity";

    private SharedPreferences mPrefs;

    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transports);

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

        // Set click listeners to buttons
        setBusButtonListener();
        setTaxiButtonListener();
    }

    private void setBusButtonListener(){

        Button button = findViewById(R.id.bus_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to bus activity
                finish();
                startActivity(new Intent(getApplicationContext(), BusActivity.class));
            }
        });
    }

    private void setTaxiButtonListener(){

        Button button = findViewById(R.id.taxi_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to bus activity
                finish();
                startActivity(new Intent(getApplicationContext(), TaxiActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
