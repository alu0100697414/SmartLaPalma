package com.example.jose.smartlapalma.Views.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jose.smartlapalma.Controllers.Adapters.NewsViewPagerAdapter;
import com.example.jose.smartlapalma.R;

public class ResidentFragment extends Fragment {

    public ResidentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_resident, container, false);

        ViewPager viewPager =  rootView.findViewById(R.id.news_viewpager);
        viewPager.setAdapter(new NewsViewPagerAdapter(getActivity()));

        return rootView;
    }
}
