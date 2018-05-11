package com.example.jose.smartlapalma.Views.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Adapters.NewsViewPagerAdapter;
import com.example.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.example.jose.smartlapalma.Models.Meteorology.DayWeather;
import com.example.jose.smartlapalma.Models.Meteorology.WeatherState;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.R;
import com.example.jose.smartlapalma.Views.Activities.InterestPlacesActivity;
import com.example.jose.smartlapalma.Views.Activities.TransportsActivity;

public class TouristFragment extends Fragment {

    private static final String TAG = "ResidentFragment";

    private View mRootView;

    public TouristFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_tourist, container, false);
        final View rootView = mRootView;

        // Set data
        setDataInView();

        // Set onclick listeners to buttons
        setClickListenersToButtons(rootView);

        // Set error if network is noot available
        checkNetworkStatus(rootView);

        return rootView;
    }

    // This method puts onclick listeners in buttons
    private void setClickListenersToButtons(View view){

        // Interest places button
        Button interestPlacesButton = view.findViewById(R.id.interest_places_button);
        interestPlacesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), InterestPlacesActivity.class));
            }
        });

        // Public transport button
        Button transportsButton = view.findViewById(R.id.transports_button);
        transportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), TransportsActivity.class));
            }
        });
    }

    // This method sets data in layout
    private void setDataInView(){

        // Hide error message possible network error
        TextView error = mRootView.findViewById(R.id.error_request);
        error.setVisibility(View.GONE);

        ImageView errorImage = mRootView.findViewById(R.id.error_imageview);
        errorImage.setVisibility(View.GONE);

        // Make visible the content
        LinearLayout linearLayout = mRootView.findViewById(R.id.linearlayout_content);
        linearLayout.setVisibility(View.VISIBLE);

        // Set weather information
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        if(!openDataLaPalma.getmWeather().getmDayWeatherList().isEmpty()){

            // Set today data (DayWeather pos: 0)
            DayWeather today = openDataLaPalma.getmWeather().getmDayWeatherList().get(0);

            // Set weather image
            ImageView imageWeather = mRootView.findViewById(R.id.weather_image);
            imageWeather.setImageResource(getDrawableFromId(today.getmSkyState().getmValue()));

            // Set max and min degrees
            TextView todayMaxDegrees = mRootView.findViewById(R.id.today_max_degrees);
            TextView todayMinDegrees = mRootView.findViewById(R.id.today_min_degrees);

            todayMaxDegrees.setText(today.getmTemperature().getmMax() + getString(R.string.degree));
            todayMinDegrees.setText(today.getmTemperature().getmMin() + getString(R.string.degree));

            // Set UV
            TextView todayUV = mRootView.findViewById(R.id.uv_value);
            todayUV.setText(today.getmUV().getmUV());

            // Set description
            TextView todayDescription = mRootView.findViewById(R.id.today_description);
            todayDescription.setText(today.getmSkyState().getmDescription());
        }
    }

    private void checkNetworkStatus(View view){

        if(!CustomUtils.isNetworkAvailable(getActivity())){

            // Make gone the content
            LinearLayout linearLayout = mRootView.findViewById(R.id.linearlayout_content);
            linearLayout.setVisibility(View.GONE);

            // Show error message of possible network error
            TextView error = view.findViewById(R.id.error_request);
            error.setVisibility(View.VISIBLE);

            ImageView errorImage = view.findViewById(R.id.error_imageview);
            errorImage.setVisibility(View.VISIBLE);
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
}
