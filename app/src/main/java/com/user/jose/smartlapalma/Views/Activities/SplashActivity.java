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
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.user.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.user.jose.smartlapalma.Controllers.Utils.GetApplicationDataTask;
import com.user.jose.smartlapalma.Models.OpenDataLaPalma;
import com.user.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.user.jose.smartlapalma.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = "SplashActivity";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private ProgressBar mProgressBar;
    private ImageButton mRestartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Progressbar and button
        mProgressBar = findViewById(R.id.splash_Progress);
        mRestartButton = findViewById(R.id.restart);

        // Shared preferences
        mPrefs = getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        // Current device language is updated
        mEditor = mPrefs.edit();
        mEditor.putString(SharedPreferencesKeys.CURRENT_LANGUAGE,
                CustomUtils.getApplicationLanguage(
                        Locale.getDefault().getDisplayLanguage().toString()));
        mEditor.commit();

        // Set restart button listener
        setRestartButtonListener();

        // Set error if network is noot available or send requests
        checkNetworkStatus();
    }

    private void setRestartButtonListener(){

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Hide error message possible network error
                LinearLayout error = findViewById(R.id.splash_network_error);
                error.setVisibility(View.GONE);

                // Show spinner when data is loaded
                mProgressBar.setVisibility(View.VISIBLE);

                // Check again
                checkNetworkStatus();
            }
        });
    }

    private void checkNetworkStatus(){

        if(!CustomUtils.isNetworkAvailable(this)){

            // Hide spinner when data is loaded
            mProgressBar.setVisibility(View.GONE);

            // Show error message possible network error
            LinearLayout error = findViewById(R.id.splash_network_error);
            error.setVisibility(View.VISIBLE);
        } else {

            // Call APIs thread
            OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

            GetApplicationDataTask apiCalls = new GetApplicationDataTask(this, openDataLaPalma);
            apiCalls.execute();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }
}
