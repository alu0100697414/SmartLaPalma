package com.user.jose.smartlapalma.Views.Activities;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.jose.smartlapalma.Models.News.New;
import com.user.jose.smartlapalma.R;
import com.squareup.picasso.Picasso;

public class NewDetailActivity extends AppCompatActivity {

    ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Customize actionbar
        mActionbar = getSupportActionBar();
        if (mActionbar != null) {
            mActionbar.setDisplayHomeAsUpEnabled(true);
        }

        updateActionBarValues();

        // Floating button
        setFloatingButtonAction();

        // Set new data
        setNewData();
    }

    private void updateActionBarValues(){

        // Get values from the previous activity
        Intent intent = getIntent();
        mActionbar.setTitle(intent.getStringExtra(New.titleNewKey));

        ImageView header = findViewById(R.id.image_header);
        Picasso.with(this).load(intent.getStringExtra(New.imageUrlKey)).into(header);
    }

    private void setFloatingButtonAction(){

        final FloatingActionButton fab = findViewById(R.id.share_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Customize string
                Intent intent = getIntent();
                String currentNew = intent.getStringExtra(New.titleNewKey).toUpperCase() + "\n\n" +
                        intent.getStringExtra(New.descriptionNewKey) + "\n\n" +
                        getString(R.string.for_more_info) + " (" +
                        getString(R.string.app_url) + ")";

                // Share new
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, currentNew);
                sendIntent.setType("text/plain");

                // Lanzamos el intent
                startActivity(Intent.createChooser(sendIntent, getString(R.string.share_option)));
            }
        });
    }

    private void setNewData(){

        Intent intent = getIntent();

        TextView description = findViewById(R.id.description);
        TextView text = findViewById(R.id.text);
        TextView date = findViewById(R.id.date);

        description.setText(intent.getStringExtra(New.descriptionNewKey));
        text.setText(intent.getStringExtra(New.textNewKey));
        date.setText(intent.getStringExtra(New.dateNewKey));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Back arrow is pressed
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
