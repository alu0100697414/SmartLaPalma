package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Models.About;
import com.example.jose.smartlapalma.Models.Meteorology.DayWeather;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;

public class MeteorologyActivity extends AppCompatActivity {

    private final String TAG = "MeteorologyActivity";

    private SharedPreferences mPrefs;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteorology);

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

        setDataInActivity();
    }

    private void setDataInActivity(){

        // Load weather information
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        if(!openDataLaPalma.getmWeather().getmDayWeatherList().isEmpty()){

            // Set today data (DayWeather pos: 0)
            DayWeather today = openDataLaPalma.getmWeather().getmDayWeatherList().get(0);

            // Set max and min degrees
            TextView todayMaxDegrees = findViewById(R.id.today_max_degrees);
            TextView todayMinDegrees = findViewById(R.id.today_min_degrees);

            todayMaxDegrees.setText(today.getmTemperature().getmMax() + getString(R.string.degree));
            todayMinDegrees.setText(today.getmTemperature().getmMin() + getString(R.string.degree));

            // Set UV
            TextView todayUV = findViewById(R.id.uv_value);
            todayUV.setText(today.getmUV().getmUV());

            // Set description
            TextView todayDescription = findViewById(R.id.today_description);
            todayDescription.setText(today.getmSkyState().getmDescription());

            // Set rain probability, wind, thermal sensation and humidity
            TextView todayRainProbability = findViewById(R.id.today_rain_probability);
            todayRainProbability.setText(today.getmPrecipitation().getmPrecipitationValue() +
                    getString(R.string.percentage));

            TextView todayWind = findViewById(R.id.today_wind);
            todayWind.setText(today.getmWind().getmVelocity() + getString(R.string.velocity));

            TextView todayThermalSensation = findViewById(R.id.today_thermal_sensation);
            todayThermalSensation.setText(today.getmThermalSensation().getmMax() +
                    getString(R.string.degree));

            TextView todayHumidity = findViewById(R.id.today_humidity);
            todayHumidity.setText(today.getmHumidity().getmMax() +
                    getString(R.string.percentage));
        }

        else {
            // TODO: SHOW LOAGING ERROR MESSAGE
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
