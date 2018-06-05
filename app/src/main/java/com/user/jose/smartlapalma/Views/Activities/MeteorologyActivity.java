package com.user.jose.smartlapalma.Views.Activities;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.user.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.user.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.user.jose.smartlapalma.Models.Meteorology.DayWeather;
import com.user.jose.smartlapalma.Models.Meteorology.WeatherState;
import com.user.jose.smartlapalma.Models.OpenDataLaPalma;
import com.user.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.user.jose.smartlapalma.Models.UserType;
import com.user.jose.smartlapalma.R;

public class MeteorologyActivity extends AppCompatActivity {

    private final String TAG = "MeteorologyActivity";

    private ViewGroup mMeteoViewItem;

    private SharedPreferences mPrefs;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteorology);
        mMeteoViewItem = findViewById(R.id.meteo_scroll_view_content);

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

            // Set weather image
            ImageView imageWeather = findViewById(R.id.weather_image);
            imageWeather.setImageResource(getDrawableFromId(today.getmSkyState().getmValue()));

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

            // Set next days predictions
            LayoutInflater inflater = LayoutInflater.from(this);
            int id = R.layout.weather_next_day_item;

            // Add all the days prediction items
            for(int i=1; i<openDataLaPalma.getmWeather().getmDayWeatherList().size(); i++){

                DayWeather currentDay = openDataLaPalma.getmWeather().getmDayWeatherList().get(i);

                // Inflate about item
                LinearLayout item = (LinearLayout) inflater.inflate(id, null, false);

                // Set content for the item
                // Set date
                TextView dateItem = item.findViewById(R.id.weather_day_item);
                dateItem.setText(getFormattedDate(currentDay.getmDate()));

                // Set weather image
                ImageView imageWeatherItem = item.findViewById(R.id.weather_image_item);
                imageWeatherItem.setImageResource(getDrawableFromId(currentDay.getmSkyState().getmValue()));

                // Set description
                TextView descriptionItem = item.findViewById(R.id.weather_description_item);
                descriptionItem.setText(currentDay.getmSkyState().getmDescription());

                // Set max and min temp
                TextView maxTemp = item.findViewById(R.id.weather_max_temp_item);
                maxTemp.setText(currentDay.getmTemperature().getmMax() + getString(R.string.degree));

                TextView minTemp = item.findViewById(R.id.weather_min_temp_item);
                minTemp.setText(currentDay.getmTemperature().getmMin() + getString(R.string.degree));

                // Custom some styles
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                params.leftMargin = CustomUtils.getDpValues(this, 25f);
                params.rightMargin = CustomUtils.getDpValues(this, 25f);
                item.setLayoutParams(params);

                // Add view in parent layout
                mMeteoViewItem.addView(item);
            }
        }

        else {
            // TODO: SHOW LOAGING ERROR MESSAGE
        }
    }

    // Get drawable image of weather
    private int getDrawableFromId(String id){

        switch (id){
            case WeatherState.DESPEJADO:
                return R.drawable.sun;
            case WeatherState.POCO_NUBOSO:
                return R.drawable.sun_little_cloud;
            case WeatherState.INTERVALOS_NUBOSOS:
                return R.drawable.sun_two_clouds;
            case WeatherState.NUBOSO:
                return R.drawable.sun_with_one_cloud;
            case WeatherState.MUY_NUBOSO:
                return R.drawable.cloud;
            case WeatherState.CUBIERTO:
                return R.drawable.cloud;
            case WeatherState.NUBES_ALTAS:
                return R.drawable.cloudy;
            case WeatherState.INTERVALOS_NUBOSOS_LLUVIA:
                return R.drawable.rain_sun_cloud;
            case WeatherState.NUBOSO_LLUVIA:
                return R.drawable.rain;
            case WeatherState.MUY_NUBOSO_LLUVIA:
                return R.drawable.rain;
            case WeatherState.CUBIERTO_LLUVIA:
                return R.drawable.rain;
            case WeatherState.CHUBASCOS:
                return R.drawable.rain;
            case WeatherState.INTERVALOS_NUBOSOS_NIEVE:
                return R.drawable.cloud_sun_snow;
            case WeatherState.NUBOSO_NIEVE:
                return R.drawable.snow;
            case WeatherState.MUY_NUBOSO_NIEVE:
                return R.drawable.snow;
            case WeatherState.CUBIERTO_NIEVE:
                return R.drawable.snow;
            case WeatherState.INTERVALOS_NUBOSOS_LLUVIA_ESCASA:
                return R.drawable.rain_sun_cloud;
            case WeatherState.NUBOSO_LLUVIA_ESCASA:
                return R.drawable.rain_sun_cloud;
            case WeatherState.MUY_NUBOSO_LLUVIA_ESCASA:
                return R.drawable.rain;
            case WeatherState.TORMENTA:
                return R.drawable.storm;
            case WeatherState.GRANIZO:
                return R.drawable.hail;
            case WeatherState.BRUMA:
                return R.drawable.cloudy;
            case WeatherState.NIEBLA:
                return R.drawable.haze;
            case WeatherState.CALIMA:
                return R.drawable.calima;
            default:
                return R.drawable.weather_default;
        }
    }

    private String getFormattedDate(String date){

        String temp[] = date.split("-");

        return temp[2] + " " + CustomUtils.getMonthFromNumber(getApplicationContext(), temp[1]);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
