package com.user.jose.smartlapalma.Views.Fragments;

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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.user.jose.smartlapalma.Controllers.Adapters.NewsViewPagerAdapter;
import com.user.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.user.jose.smartlapalma.Models.Meteorology.DayWeather;
import com.user.jose.smartlapalma.Models.Meteorology.WeatherState;
import com.user.jose.smartlapalma.Models.News.New;
import com.user.jose.smartlapalma.Models.OpenDataLaPalma;
import com.user.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.user.jose.smartlapalma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResidentFragment extends Fragment {

    private static final String TAG = "ResidentFragment";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private SharedPreferences mPrefs;

    private LinearLayout mProgressBar;
    private View mRootView;

    private ArrayList<New> mNewsList;

    public ResidentFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_resident, container, false);
        final View rootView = mRootView;

        // Shared preferences
        mPrefs = getActivity().getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        mProgressBar = rootView.findViewById(R.id.linlaHeaderProgress);
        mProgressBar.setVisibility(View.VISIBLE);

        // Init arraylist
        mNewsList = new ArrayList<>();

        // Get Firebase data and set listener
        DatabaseReference ref = database.getReference("news/" +
                mPrefs.getString(SharedPreferencesKeys.CURRENT_LANGUAGE, "español"));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Clear previous data
                mNewsList.clear();

                // Values are added to news list
                for (DataSnapshot eventSnapshot: dataSnapshot.getChildren()) {

                    New currentNew = new New(
                            (String) eventSnapshot.child(New.titleNewKey).getValue(),
                            (String) eventSnapshot.child(New.descriptionNewKey).getValue(),
                            (String) eventSnapshot.child(New.textNewKey).getValue(),
                            (String) eventSnapshot.child(New.dateNewKey).getValue(),
                            (String) eventSnapshot.child(New.imageUrlKey).getValue()
                    );

                    mNewsList.add(currentNew);
                }

                // Set data
                setDataInView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hide spinner when data is loaded
                mProgressBar.setVisibility(View.GONE);

                // Show error message in layout
                TextView errorText = rootView.findViewById(R.id.error_request);
                errorText.setVisibility(View.VISIBLE);

                ImageView errorImage = rootView.findViewById(R.id.error_imageview);
                errorImage.setVisibility(View.VISIBLE);
            }
        });

        // Set error if network is noot available
        checkNetworkStatus(rootView);

        return rootView;
    }

    // This method sets firebase data in the layout
    private void setDataInView(){

        if(getActivity() != null){

            // Hide spinner when data is loaded
            mProgressBar.setVisibility(View.GONE);

            // Hide error message possible network error
            TextView error = mRootView.findViewById(R.id.error_request);
            error.setVisibility(View.GONE);

            ImageView errorImage = mRootView.findViewById(R.id.error_imageview);
            errorImage.setVisibility(View.GONE);

            // Make visible the content
            ScrollView scrollView = mRootView.findViewById(R.id.resident_fragment_scroll_view);
            scrollView.setVisibility(View.VISIBLE);

            // Set weather information
            // Load weather information
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

            // Set news in view
            ViewPager viewPager =  getActivity().findViewById(R.id.news_viewpager);
            viewPager.setAdapter(new NewsViewPagerAdapter(getActivity(), mNewsList));
        }
    }

    private void checkNetworkStatus(View view){

        if(!CustomUtils.isNetworkAvailable(getActivity())){

            // Hide spinner when data is loaded
            mProgressBar.setVisibility(View.GONE);

            // Show error message possible network error
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
