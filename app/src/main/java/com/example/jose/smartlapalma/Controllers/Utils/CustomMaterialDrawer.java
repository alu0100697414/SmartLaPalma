package com.example.jose.smartlapalma.Controllers.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.User;
import com.example.jose.smartlapalma.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
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
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, User.TOURIST_USER);
                            } else {
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, User.RESIDENT_USER);
                            }

                            mEditor.commit();

                            // Activity is restarted
                            restartActivity();
                        }

                        return false;
                    }
                }).build();

        // Drawer list items
        PrimaryDrawerItem initItem = new PrimaryDrawerItem().withIdentifier(1).withName(mActivity.getString(R.string.init));
        SecondaryDrawerItem newsItem = new SecondaryDrawerItem().withIdentifier(2).withName(mActivity.getString(R.string.news));
        SecondaryDrawerItem meteorologyItem = new SecondaryDrawerItem().withIdentifier(3).withName(mActivity.getString(R.string.meteorology));
        SecondaryDrawerItem transportItem = new SecondaryDrawerItem().withIdentifier(4).withName(mActivity.getString(R.string.transport));
        SecondaryDrawerItem interest_placesItem = new SecondaryDrawerItem().withIdentifier(5).withName(mActivity.getString(R.string.interest_places));
        SecondaryDrawerItem contactItem = new SecondaryDrawerItem().withIdentifier(6).withName(mActivity.getString(R.string.contact));
        SecondaryDrawerItem aboutItem = new SecondaryDrawerItem().withIdentifier(7).withName(mActivity.getString(R.string.about));
        SecondaryDrawerItem exitItem = new SecondaryDrawerItem().withIdentifier(8).withName(mActivity.getString(R.string.exit));


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
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        // Menu actions
                        switch ((int) drawerItem.getIdentifier()){

                            case 8: // Closes application
                                closeApplication();
                                break;
                        }

                        return true;
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
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, User.TOURIST_USER);
                            } else {
                                mEditor.putInt(SharedPreferencesKeys.USER_TYPE, User.RESIDENT_USER);
                            }

                            mEditor.commit();

                            // Activity is restarted
                            restartActivity();
                        }

                        return false;
                    }
                }).build();

        // Drawer list items
        PrimaryDrawerItem initItem = new PrimaryDrawerItem().withIdentifier(1).withName(mActivity.getString(R.string.init));
        SecondaryDrawerItem newsItem = new SecondaryDrawerItem().withIdentifier(2).withName(mActivity.getString(R.string.news));
        SecondaryDrawerItem meteorologyItem = new SecondaryDrawerItem().withIdentifier(3).withName(mActivity.getString(R.string.meteorology));
        SecondaryDrawerItem transportItem = new SecondaryDrawerItem().withIdentifier(4).withName(mActivity.getString(R.string.transport));
        SecondaryDrawerItem interest_placesItem = new SecondaryDrawerItem().withIdentifier(5).withName(mActivity.getString(R.string.interest_places));
        SecondaryDrawerItem contactItem = new SecondaryDrawerItem().withIdentifier(6).withName(mActivity.getString(R.string.contact));
        SecondaryDrawerItem aboutItem = new SecondaryDrawerItem().withIdentifier(7).withName(mActivity.getString(R.string.about));
        SecondaryDrawerItem exitItem = new SecondaryDrawerItem().withIdentifier(8).withName(mActivity.getString(R.string.exit));


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
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        // Menu actions
                        switch ((int) drawerItem.getIdentifier()){

                            case 8: // Closes application
                                closeApplication();
                                break;
                        }

                        return true;
                    }
                }).build();
    }

    // This function restarts the activity
    private void restartActivity(){
        Intent intent = mActivity.getIntent();
        mActivity.finish();
        mActivity.startActivity(intent);
    }

    // This function closes the application
    private void closeApplication(){
        mActivity.finish();
    }
}
