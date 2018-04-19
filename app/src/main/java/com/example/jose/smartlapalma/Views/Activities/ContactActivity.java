package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Controllers.Utils.CustomUtils;
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
    private ImageView mEmailImageView;

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
        DatabaseReference ref = database.getReference("contact/" +
                mPrefs.getString(SharedPreferencesKeys.CURRENT_LANGUAGE, "español"));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Values are saved
                mContact.setmWelcomeMessage(
                        (String) dataSnapshot.child(Contact.welcomeMessageKey).getValue());
                mContact.setmTextMessage(
                        (String) dataSnapshot.child(Contact.textMessageKey).getValue());
                mContact.setmEmail(
                        (String) dataSnapshot.child(Contact.emailKey).getValue());
                mContact.setmLastMessage(
                        (String) dataSnapshot.child(Contact.lastMessageKey).getValue());

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

        // Set data in textviews
        TextView welcome = findViewById(R.id.welcome_textview);
        TextView text = findViewById(R.id.text_textview);
        TextView email = findViewById(R.id.email_textview);
        TextView lastText = findViewById(R.id.last_text_textview);

        welcome.setText((mContact.getmWelcomeMessage()));
        text.setText(mContact.getmTextMessage());
        email.setText(mContact.getmEmail());
        lastText.setText(mContact.getmLastMessage());

        // Set visibility to email image icon
        ImageView emailImage = findViewById(R.id.email_imageview);
        emailImage.setVisibility(View.VISIBLE);

        // Set image view clickable for gmail intent
        mEmailImageView = findViewById(R.id.email_imageview);
        mEmailImageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Send gmail intent
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", mContact.getmEmail(), null));
                startActivity(Intent.createChooser(emailIntent, null));
            }
        });
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
