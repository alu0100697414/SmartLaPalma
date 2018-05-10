package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.example.jose.smartlapalma.Views.Fragments.ResidentFragment;
import com.example.jose.smartlapalma.Views.Fragments.TouristFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;

    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            // Load resident fragment
            if (savedInstanceState == null) {
                ResidentFragment fragment = new ResidentFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_fragment, fragment)
                        .commit();
            }
        } else {
            customMaterialDrawer.setTouristMode();

            // Load resident fragment
            if (savedInstanceState == null) {
                TouristFragment fragment = new TouristFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.main_fragment, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
