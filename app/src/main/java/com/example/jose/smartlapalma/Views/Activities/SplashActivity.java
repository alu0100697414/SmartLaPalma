package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.example.jose.smartlapalma.Controllers.Utils.GetApplicationDataTask;
import com.example.jose.smartlapalma.Controllers.Utils.Request;
import com.example.jose.smartlapalma.Models.OpenDataLaPalma;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private final String TAG = "SplashActivity";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Shared preferences
        mPrefs = getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        // Current device language is updated
        mEditor = mPrefs.edit();
        mEditor.putString(SharedPreferencesKeys.CURRENT_LANGUAGE,
                Locale.getDefault().getDisplayLanguage().toString());
        mEditor.commit();

        // Call APIs thread
        OpenDataLaPalma openDataLaPalma = OpenDataLaPalma.getInstance();

        GetApplicationDataTask apiCalls = new GetApplicationDataTask(this, openDataLaPalma);
        apiCalls.execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }
}
