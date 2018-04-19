package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Adapters.NewsListAdapter;
import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.example.jose.smartlapalma.Models.New;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private final String TAG = "NewsActivity";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private SharedPreferences mPrefs;

    private LinearLayout mProgressBar;

    private ListView listView;
    private NewsListAdapter mAdapter;

    private ArrayList<New> mNewsList;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.linlaHeaderProgress);
        mProgressBar.setVisibility(View.VISIBLE);

        // Init listview and arraylist
        listView = findViewById(R.id.news_list);
        mNewsList = new ArrayList<>();

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
                    mNewsList.add(new New(
                            (String) eventSnapshot.child(New.titleNewKey).getValue(),
                            (String) eventSnapshot.child(New.descriptionNewKey).getValue(),
                            (String) eventSnapshot.child(New.textNewKey).getValue(),
                            (String) eventSnapshot.child(New.dateNewKey).getValue()
                    ));
                }

                // Update layout
                setDataInView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hide spinner when data is loaded
                mProgressBar.setVisibility(View.GONE);

                // Show error message in layout
                ImageView errorImage = findViewById(R.id.error_imageview);
                errorImage.setVisibility(View.VISIBLE);
            }
        });

        // Set error if network is noot available
        checkNetworkStatus();
    }

    // This method sets firebase data in the layout
    private void setDataInView(){

        // Hide spinner when data is loaded
        mProgressBar.setVisibility(View.GONE);

        // Hide error message possible network error
        TextView error = findViewById(R.id.error_request);
        error.setVisibility(View.GONE);

        ImageView errorImage = findViewById(R.id.error_imageview);
        errorImage.setVisibility(View.GONE);

        // Set data in view
        mAdapter = new NewsListAdapter(this, mNewsList);
        listView.setAdapter(mAdapter);
    }

    private void checkNetworkStatus(){

        if(!CustomUtils.isNetworkAvailable(this)){

            // Hide spinner when data is loaded
            mProgressBar.setVisibility(View.GONE);

            // Show error message possible network error
            TextView error = findViewById(R.id.error_request);
            error.setVisibility(View.VISIBLE);

            ImageView errorImage = findViewById(R.id.error_imageview);
            errorImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
