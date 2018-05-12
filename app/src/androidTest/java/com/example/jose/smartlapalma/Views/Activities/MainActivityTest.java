package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.example.jose.smartlapalma.Views.Fragments.ResidentFragment;
import com.example.jose.smartlapalma.Views.Fragments.TouristFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

import static org.hamcrest.core.StringContains.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkMainActivityFragmentsIsDisplayed() throws InterruptedException {

        Thread.sleep(10000);

        // Shared preferences
        SharedPreferences mPrefs = mActivityTestRule.getActivity().getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        int typeUser = mPrefs.getInt(SharedPreferencesKeys.USER_TYPE, UserType.RESIDENT_USER);

        // Check tourist components
        if(typeUser == UserType.RESIDENT_USER){

            ResidentFragment fragment = new ResidentFragment();
            mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();

            onView(allOf(withId(R.id.resident_fragment_scroll_view), isDisplayed()));
            onView(allOf(withId(R.id.meteo_scroll_view_content), isDisplayed()));
            onView(allOf(withId(R.id.weather_image), isDisplayed()));
            onView(allOf(withId(R.id.today_max_degrees), isDisplayed()));
            onView(allOf(withId(R.id.today_min_degrees), isDisplayed()));
            onView(allOf(withId(R.id.uv_value), isDisplayed()));
            onView(allOf(withId(R.id.today_description), isDisplayed()));
            onView(allOf(withId(R.id.news_viewpager), isDisplayed()));
        }

        if(typeUser == UserType.TOURIST_USER){

            TouristFragment fragment = new TouristFragment();
            mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();

            onView(allOf(withId(R.id.linearlayout_content), isDisplayed()));
            onView(allOf(withId(R.id.meteo_scroll_view_content), isDisplayed()));
            onView(allOf(withId(R.id.weather_image), isDisplayed()));
            onView(allOf(withId(R.id.today_max_degrees), isDisplayed()));
            onView(allOf(withId(R.id.today_min_degrees), isDisplayed()));
            onView(allOf(withId(R.id.uv_value), isDisplayed()));
            onView(allOf(withId(R.id.today_description), isDisplayed()));
            onView(allOf(withId(R.id.interest_places_button), isDisplayed()));
            onView(allOf(withId(R.id.transports_button), isDisplayed()));
        }
    }

    @Test
    public void checkMainActivityFragmentsTextViewNotEmpty() throws InterruptedException {

        Thread.sleep(10000);

        onView(allOf(withId(R.id.today_max_degrees), not(withText(containsString("")))));
        onView(allOf(withId(R.id.today_min_degrees), not(withText(containsString("")))));
        onView(allOf(withId(R.id.uv_value), not(withText(containsString("")))));
        onView(allOf(withId(R.id.today_description), not(withText(containsString("")))));
    }

    @Test
    public void checkMainActivityFragmentsStaticTextViews() throws InterruptedException {

        Thread.sleep(10000);

        // Shared preferences
        SharedPreferences mPrefs = mActivityTestRule.getActivity().getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        int typeUser = mPrefs.getInt(SharedPreferencesKeys.USER_TYPE, UserType.RESIDENT_USER);

        // Check tourist components
        if(typeUser == UserType.RESIDENT_USER){

            ResidentFragment fragment = new ResidentFragment();
            mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();

            onView(allOf(withId(R.id.weather_today), withText(containsString(mActivityTestRule.getActivity().getString(R.id.weather_today)))));
            onView(allOf(withId(R.id.resident_fragment_weather_subtitle), withText(containsString(mActivityTestRule.getActivity().getString(R.id.resident_fragment_weather_subtitle)))));
            onView(allOf(withId(R.id.resident_fragment_news_title), withText(containsString(mActivityTestRule.getActivity().getString(R.id.resident_fragment_news_title)))));
            onView(allOf(withId(R.id.resident_fragment_news_subtitle), withText(containsString(mActivityTestRule.getActivity().getString(R.id.resident_fragment_news_subtitle)))));
        }

        if(typeUser == UserType.TOURIST_USER){

            TouristFragment fragment = new TouristFragment();
            mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();

            onView(allOf(withId(R.id.weather_today), withText(containsString(mActivityTestRule.getActivity().getString(R.id.weather_today)))));
            onView(allOf(withId(R.id.resident_fragment_weather_subtitle), withText(containsString(mActivityTestRule.getActivity().getString(R.id.resident_fragment_weather_subtitle)))));
        }
    }
}
