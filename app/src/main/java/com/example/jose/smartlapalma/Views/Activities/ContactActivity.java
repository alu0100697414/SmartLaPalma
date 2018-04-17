package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Models.Contact;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ContactActivity extends AppCompatActivity {

    private final String TAG = "ContactActivity";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private SharedPreferences mPrefs;

    private LinearLayout mProgressBar;

    private Contact mContact;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressBar = findViewById(R.id.linlaHeaderProgress);
        mProgressBar.setVisibility(View.VISIBLE);

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

        // Init object where contact data is saved
        mContact = new Contact();

        // Get Firebase data and set listener
        DatabaseReference ref = database.getReference("contact/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Values are saved
                mContact.setmWelcomeMessage(
                        (HashMap) dataSnapshot.child(Contact.welcomeMessageKey).getValue());
                mContact.setmTextMessage(
                        (HashMap) dataSnapshot.child(Contact.textMessageKey).getValue());
                mContact.setmEmail(
                        (HashMap) dataSnapshot.child(Contact.emailKey).getValue());
                mContact.setmLastMessage(
                        (HashMap) dataSnapshot.child(Contact.lastMessageKey).getValue());

                // Update layout
                setDataInView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "The read failed: " + databaseError.getCode());
            }
        });
    }

    // This method sets firebase data in the layout
    private void setDataInView(){

        // Set data in textviews
        TextView test = findViewById(R.id.test);
        test.setText((String) mContact.getmTextMessage()
                        .get(mPrefs.getString(SharedPreferencesKeys.CURRENT_LANGUAGE, "English")));

        // Hide spinner when data is loaded
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }
}
