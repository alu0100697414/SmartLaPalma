package com.example.jose.smartlapalma.Views.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jose.smartlapalma.Controllers.Adapters.NewsListAdapter;
import com.example.jose.smartlapalma.Controllers.Adapters.NewsViewPagerAdapter;
import com.example.jose.smartlapalma.Controllers.Utils.CustomUtils;
import com.example.jose.smartlapalma.Models.News.New;
import com.example.jose.smartlapalma.Models.SharedPreferencesKeys;
import com.example.jose.smartlapalma.Models.UserType;
import com.example.jose.smartlapalma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResidentFragment extends Fragment {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private SharedPreferences mPrefs;

    private LinearLayout mProgressBar;
    private View mRootView;

    private ArrayList<New> mNewsList;

    public ResidentFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_resident, container, false);
        final View rootView = mRootView;

        // Shared preferences
        mPrefs = getActivity().getSharedPreferences(
                SharedPreferencesKeys.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        mProgressBar = rootView.findViewById(R.id.linlaHeaderProgress);
        mProgressBar.setVisibility(View.VISIBLE);

        // Init arraylist
        mNewsList = new ArrayList<>();

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

                    New currentNew = new New(
                            (String) eventSnapshot.child(New.titleNewKey).getValue(),
                            (String) eventSnapshot.child(New.descriptionNewKey).getValue(),
                            (String) eventSnapshot.child(New.textNewKey).getValue(),
                            (String) eventSnapshot.child(New.dateNewKey).getValue(),
                            (String) eventSnapshot.child(New.imageUrlKey).getValue()
                    );

                    mNewsList.add(currentNew);
                }

                // Set data
                setDataInView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hide spinner when data is loaded
                mProgressBar.setVisibility(View.GONE);

                // Show error message in layout
                TextView errorText = rootView.findViewById(R.id.error_request);
                errorText.setVisibility(View.VISIBLE);

                ImageView errorImage = rootView.findViewById(R.id.error_imageview);
                errorImage.setVisibility(View.VISIBLE);
            }
        });

        // Set error if network is noot available
        checkNetworkStatus();

        return rootView;
    }

    // This method sets firebase data in the layout
    private void setDataInView(){

        // Hide spinner when data is loaded
        mProgressBar.setVisibility(View.GONE);

        // Hide error message possible network error
        TextView error = mRootView.findViewById(R.id.error_request);
        error.setVisibility(View.GONE);

        ImageView errorImage = mRootView.findViewById(R.id.error_imageview);
        errorImage.setVisibility(View.GONE);

        // Make visible the content
        ScrollView scrollView = mRootView.findViewById(R.id.resident_fragment_scroll_view);
        scrollView.setVisibility(View.VISIBLE);

        // Set data in view
        ViewPager viewPager =  getActivity().findViewById(R.id.news_viewpager);
        viewPager.setAdapter(new NewsViewPagerAdapter(getActivity(), mNewsList));
    }

    private void checkNetworkStatus(){

        if(!CustomUtils.isNetworkAvailable(getActivity())){

            // Hide spinner when data is loaded
            mProgressBar.setVisibility(View.GONE);

            // Show error message possible network error
            TextView error = getActivity().findViewById(R.id.error_request);
            error.setVisibility(View.VISIBLE);

            ImageView errorImage = getActivity().findViewById(R.id.error_imageview);
            errorImage.setVisibility(View.VISIBLE);
        }
    }
}
