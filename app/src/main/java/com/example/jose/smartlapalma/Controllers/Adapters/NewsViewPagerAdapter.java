package com.example.jose.smartlapalma.Controllers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jose.smartlapalma.Models.News.New;
import com.example.jose.smartlapalma.R;
import com.example.jose.smartlapalma.Views.Activities.NewDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsViewPagerAdapter extends PagerAdapter {

    private static final int LAST_NEWS_NUMBER = 3;

    private Context mContext;

    private ArrayList<New> mNewsList;

    public NewsViewPagerAdapter(Context context, ArrayList<New> list) {
        mContext = context;
        mNewsList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.news_viewpager_item, collection,
                false);

        // Get current object
        final New currentNew = mNewsList.get(position);

        // Set click listener
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to detail new activity
                Intent intent = new Intent(mContext, NewDetailActivity.class);

                intent.putExtra(New.titleNewKey, currentNew.getmTitle());
                intent.putExtra(New.imageUrlKey, currentNew.getmImageUrl());
                intent.putExtra(New.descriptionNewKey, currentNew.getmDescription());
                intent.putExtra(New.textNewKey, currentNew.getmText());
                intent.putExtra(New.dateNewKey, currentNew.getmDate());

                mContext.startActivity(intent);
            }
        });

        // Set values in item
        TextView title = layout.findViewById(R.id.title_item);
        title.setText(currentNew.getmTitle());

        TextView subtitle = layout.findViewById(R.id.subtitle_item);
        subtitle.setText(currentNew.getmDescription());

        final RelativeLayout background = layout.findViewById(R.id.relativelayout_viewpager_item);
        final ImageView temporalImage = new ImageView(mContext);

        final LinearLayout spinner = layout.findViewById(R.id.image_item_progress);
        spinner.setVisibility(View.VISIBLE);

        Picasso.with(mContext).load(currentNew.getmImageUrl())
                .into(temporalImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        spinner.setVisibility(View.GONE);
                        background.setBackground(temporalImage.getDrawable());
                    }

                    @Override
                    public void onError() {

                    }
                });

        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return LAST_NEWS_NUMBER;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
