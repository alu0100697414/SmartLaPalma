package com.example.jose.smartlapalma.Views.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Utils.CustomMaterialDrawer;
import com.example.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.example.jose.smartlapalma.Models.About;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutActivity extends AppCompatActivity {

    private final String TAG = "AboutActivity";

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private SharedPreferences mPrefs;

    private LinearLayout mProgressBar;
    private ViewGroup mAboutViewItem;
    private ScrollView scrollView;

    private About mAbout;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mAboutViewItem = findViewById(R.id.scroll_view_content);
        scrollView = findViewById(R.id.about_scroll_view);

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

        // Init object where about data is saved
        mAbout = new About();

        // Get Firebase data and set listener
        DatabaseReference ref = database.getReference("about/" +
                mPrefs.getString(SharedPreferencesKeys.CURRENT_LANGUAGE, "español"));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Number of questions
                int nQuestions = 0;

                // Values are saved
                for (DataSnapshot eventSnapshot: dataSnapshot.getChildren()) {

                    if(!eventSnapshot.getKey().equals(About.mIntroTextKey)){

                        // Save question and answer
                        mAbout.setmQuestion(
                                (String) eventSnapshot.child(About.mQuestionKey).getValue());
                        mAbout.setmAnswer(
                                (String) eventSnapshot.child(About.mAnswerKey).getValue());

                        // Add up a question
                        nQuestions ++;
                    }
                }

                mAbout.setmIntroText(
                        (String) dataSnapshot.child(About.mIntroTextKey).getValue());

                mAbout.setmTotalNumber(nQuestions);

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
        TextView intro = findViewById(R.id.intro_textview);
        intro.setText(mAbout.getmIntroText());

        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.about_item;

        // Add all the questions/answers
        for(int i=0; i<mAbout.getmTotalNumber(); i++){

            // Inflate about item
            LinearLayout item = (LinearLayout) inflater.inflate(id, null, false);

            // Set content for the item
            TextView textView = item.findViewById(R.id.question_item);
            textView.setText(mAbout.getmQuestions().get(i));

            TextView answer = item.findViewById(R.id.answer_item);
            answer.setText(mAbout.getmAnswers().get(i));

            // Custom some styles
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.leftMargin = CustomUtils.getDpValues(this, 25f);
            params.rightMargin = CustomUtils.getDpValues(this, 25f);
            item.setLayoutParams(params);

            // Add view in parent layout
            mAboutViewItem.addView(item);
        }
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
