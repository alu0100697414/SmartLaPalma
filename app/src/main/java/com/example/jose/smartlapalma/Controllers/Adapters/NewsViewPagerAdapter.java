package com.example.jose.smartlapalma.Controllers.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jose.smartlapalma.R;

import java.util.ArrayList;

public class NewsViewPagerAdapter extends PagerAdapter {

    private ArrayList<String> items;

    private Context mContext;

    public NewsViewPagerAdapter(Context context) {
        mContext = context;

        items = new ArrayList<>();
        items.add("AAAAAAAA");
        items.add("BBBBBBBB");
        items.add("CCCCCCCC");
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.news_viewpager_item, collection,
                false);

        TextView textView = layout.findViewById(R.id.test_viewpager_item);
        textView.setText(items.get(position));

        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
