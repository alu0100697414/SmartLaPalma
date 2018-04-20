package com.example.jose.smartlapalma.Controllers.Adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jose.smartlapalma.Models.New;
import com.example.jose.smartlapalma.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<New> {

    private Context mContext;
    private List<New> mNewsList;

    // Constructor
    public NewsListAdapter(@NonNull Context context, ArrayList<New> list) {

        super(context, 0 , list);

        mNewsList = new ArrayList<>();

        mContext = context;
        mNewsList = list;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        // Creates view if it is not exist
        if(listItem == null){
            listItem = LayoutInflater.from(mContext)
                    .inflate(R.layout.new_list_item, parent, false);
        }

        // Get current object
        New currentNew = mNewsList.get(position);

        // Set values in textviews
        TextView date = listItem.findViewById(R.id.date_item);
        date.setText(currentNew.getmDate());

        TextView title = listItem.findViewById(R.id.title_item);
        title.setText(currentNew.getmTitle());

        final RelativeLayout background = listItem.findViewById(R.id.parent_new_list_item);
        final ImageView temporalImage = new ImageView(mContext);

        final LinearLayout spinner = listItem.findViewById(R.id.linlaHeaderProgress);
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

        return listItem;
    }
}