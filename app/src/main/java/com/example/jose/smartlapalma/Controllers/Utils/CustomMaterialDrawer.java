package com.example.jose.smartlapalma.Controllers.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.example.jose.smartlapalma.Views.Activities.AboutActivity;
import com.example.jose.smartlapalma.Views.Activities.ContactActivity;
import com.example.jose.smartlapalma.Views.Activities.InterestPlacesActivity;
import com.example.jose.smartlapalma.Views.Activities.MainActivity;
import com.example.jose.smartlapalma.Views.Activities.MeteorologyActivity;
import com.example.jose.smartlapalma.Views.Activities.NewsActivity;
import com.example.jose.smartlapalma.Views.Activities.TransportsActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by Jose on 16/04/2018.
 */

public class CustomMaterialDrawer {

    private Activity mActivity;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public CustomMaterialDrawer(Activity activity){

        mActivity = activity;

        // Shared preferences
        mPrefs = mActivity.getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        // Init material drawer
        new DrawerBuilder().withActivity(mActivity).build();
    }


    public void setResidentMode(){

        // Header configuration
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withHeaderBackground(R.drawable.drawer_background)
                .addProfiles(
                        new ProfileDrawerItem().
                                withName(mActivity.getString(R.string.resident)).
                                withIcon(mActivity.getResources().getDrawable(R.drawable.resident)),
                        new ProfileDrawerItem().
                                withName(mActivity.getString(R.string.tourist)).
                                withIcon(mActivity.getResources().getDrawable(R.drawable.tourist))
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        // Check if user has been selected other profile type
                        if(currentProfile == false){

                            // Current user type is updated
                            mEditor = mPrefs.edit();

                            if(profile.getName().toString().equals(mActivity.getString(R.string.tourist))){
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, UserType.TOURIST_USER);
                            } else {
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, UserType.RESIDENT_USER);
                            }

                            mEditor.commit();

                            // App is restarted
                            restartApp();
                        }

                        return false;
                    }
                }).build();

        // Drawer list items
        SecondaryDrawerItem initItem = new SecondaryDrawerItem().withIdentifier(1).withName(mActivity.getString(R.string.init)).withSelectable(false);
        SecondaryDrawerItem newsItem = new SecondaryDrawerItem().withIdentifier(2).withName(mActivity.getString(R.string.news)).withSelectable(false);
        SecondaryDrawerItem meteorologyItem = new SecondaryDrawerItem().withIdentifier(3).withName(mActivity.getString(R.string.meteorology)).withSelectable(false);
        SecondaryDrawerItem transportItem = new SecondaryDrawerItem().withIdentifier(4).withName(mActivity.getString(R.string.transport)).withSelectable(false);
        SecondaryDrawerItem interest_placesItem = new SecondaryDrawerItem().withIdentifier(5).withName(mActivity.getString(R.string.interest_places)).withSelectable(false);
        SecondaryDrawerItem contactItem = new SecondaryDrawerItem().withIdentifier(6).withName(mActivity.getString(R.string.contact)).withSelectable(false);
        SecondaryDrawerItem aboutItem = new SecondaryDrawerItem().withIdentifier(7).withName(mActivity.getString(R.string.about)).withSelectable(false);
        SecondaryDrawerItem exitItem = new SecondaryDrawerItem().withIdentifier(8).withName(mActivity.getString(R.string.exit)).withSelectable(false);


        // Create the drawer with the items and the previous header
        new DrawerBuilder()
                .withActivity(mActivity)
                .withAccountHeader(headerResult)
                .withToolbar((Toolbar) mActivity.findViewById(R.id.toolbar))
                .addDrawerItems(
                        initItem,
                        new DividerDrawerItem(),
                        newsItem,
                        new DividerDrawerItem(),
                        meteorologyItem,
                        new DividerDrawerItem(),
                        transportItem,
                        interest_placesItem,
                        new DividerDrawerItem(),
                        contactItem,
                        aboutItem,
                        new DividerDrawerItem(),
                        exitItem
                )
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        // Current screen in menu is updated
                        mEditor = mPrefs.edit();
                        mEditor.putInt(SharedPreferencesKeys.CURRENT_SCREEN_ID,
                                (int) drawerItem.getIdentifier());
                        mEditor.commit();

                        // Menu actions
                        switch ((int) drawerItem.getIdentifier()){

                            case 2: // Open news activity
                                openNewsActivity();
                                break;

                            case 3: // Open meteo activity
                                openMeteoActivity();
                                break;

                            case 4: // Open transports activity
                                openTransportsActivity();
                                break;

                            case 5: // Open interest places activity
                                openInterestPlacesActivity();
                                break;

                            case 6: // Open contact activity
                                openContactActivity();
                                break;

                            case 7: // Open about activity
                                openAboutActivity();
                                break;

                            case 8: // Closes application
                                closeApplication();
                                break;
                        }

                        return false;
                    }
                }).build();
    }


    public void setTouristMode(){

        // Header configuration
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withHeaderBackground(R.drawable.drawer_background)
                .addProfiles(
                        new ProfileDrawerItem().
                                withName(mActivity.getString(R.string.tourist)).
                                withIcon(mActivity.getResources().getDrawable(R.drawable.tourist)),
                        new ProfileDrawerItem().
                                withName(mActivity.getString(R.string.resident)).
                                withIcon(mActivity.getResources().getDrawable(R.drawable.resident))
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        // Check if user has been selected other profile type
                        if(currentProfile == false){

                            // Current user type is updated
                            mEditor = mPrefs.edit();

                            if(profile.getName().toString().equals(mActivity.getString(R.string.tourist))){
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, UserType.TOURIST_USER);
                            } else {
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, UserType.RESIDENT_USER);
                            }

                            mEditor.commit();

                            // App is restarted
                            restartApp();
                        }

                        return false;
                    }
                }).build();

        // Drawer list items
        SecondaryDrawerItem initItem = new SecondaryDrawerItem().withIdentifier(1).withName(mActivity.getString(R.string.init)).withSelectable(false);
        SecondaryDrawerItem newsItem = new SecondaryDrawerItem().withIdentifier(2).withName(mActivity.getString(R.string.news)).withSelectable(false);
        SecondaryDrawerItem meteorologyItem = new SecondaryDrawerItem().withIdentifier(3).withName(mActivity.getString(R.string.meteorology)).withSelectable(false);
        SecondaryDrawerItem transportItem = new SecondaryDrawerItem().withIdentifier(4).withName(mActivity.getString(R.string.transport)).withSelectable(false);
        SecondaryDrawerItem interest_placesItem = new SecondaryDrawerItem().withIdentifier(5).withName(mActivity.getString(R.string.interest_places)).withSelectable(false);
        SecondaryDrawerItem contactItem = new SecondaryDrawerItem().withIdentifier(6).withName(mActivity.getString(R.string.contact)).withSelectable(false);
        SecondaryDrawerItem aboutItem = new SecondaryDrawerItem().withIdentifier(7).withName(mActivity.getString(R.string.about)).withSelectable(false);
        SecondaryDrawerItem exitItem = new SecondaryDrawerItem().withIdentifier(8).withName(mActivity.getString(R.string.exit)).withSelectable(false);


        // Create the drawer with the items and the previous header
        new DrawerBuilder()
                .withActivity(mActivity)
                .withAccountHeader(headerResult)
                .withToolbar((Toolbar) mActivity.findViewById(R.id.toolbar))
                .addDrawerItems(
                        initItem,
                        new DividerDrawerItem(),
                        transportItem,
                        interest_placesItem,
                        new DividerDrawerItem(),
                        meteorologyItem,
                        new DividerDrawerItem(),
                        newsItem,
                        new DividerDrawerItem(),
                        contactItem,
                        aboutItem,
                        new DividerDrawerItem(),
                        exitItem
                )
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        // Current screen in menu is updated
                        mEditor = mPrefs.edit();
                        mEditor.putInt(SharedPreferencesKeys.CURRENT_SCREEN_ID,
                                (int) drawerItem.getIdentifier());
                        mEditor.commit();

                        // Menu actions
                        switch ((int) drawerItem.getIdentifier()){

                            case 2: // Open news activity
                                openNewsActivity();
                                break;

                            case 3: // Open meteo activity
                                openMeteoActivity();
                                break;

                            case 4: // Open transports activity
                                openTransportsActivity();
                                break;

                            case 5: // Open interest places activity
                                openInterestPlacesActivity();
                                break;

                            case 6: // Open contact activity
                                openContactActivity();
                                break;

                            case 7: // Open about activity
                                openAboutActivity();
                                break;

                            case 8: // Closes application
                                closeApplication();
                                break;
                        }

                        return false;
                    }
                }).build();
    }

    // This function open the news screen
    private void openNewsActivity(){

        Intent intent = new Intent(mActivity, NewsActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }

        mActivity.startActivity(intent);
    }

    // This function open the meteo screen
    private void openMeteoActivity(){

        Intent intent = new Intent(mActivity, MeteorologyActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }

        mActivity.startActivity(intent);
    }

    // This function open the contact screen
    private void openContactActivity(){

        Intent intent = new Intent(mActivity, ContactActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }

        mActivity.startActivity(intent);
    }

    // This function open the transports screen
    private void openTransportsActivity(){

        Intent intent = new Intent(mActivity, TransportsActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }

        mActivity.startActivity(intent);
    }

    private void openInterestPlacesActivity(){

        Intent intent = new Intent(mActivity, InterestPlacesActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }

        mActivity.startActivity(intent);
    }

    // This function open the about screen
    private void openAboutActivity(){

        Intent intent = new Intent(mActivity, AboutActivity.class);

        if(!mActivity.getClass().getSimpleName().equals("MainActivity")){
            mActivity.finish();
        }
        
        mActivity.startActivity(intent);
    }

    // This function restarts the activity
    private void restartApp(){
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mActivity.finish();
        mActivity.startActivity(intent);
    }

    // This function closes the application
    private void closeApplication(){
        mActivity.finishAffinity();
    }
}
