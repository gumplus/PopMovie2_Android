package com.example.cyp.popmovie2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by apple on 16/5/5.
 */
public class DetailActivity extends AppCompatActivity {

    private static String videoAPi = "http://api.themoviedb.org/3/movie/id/videos";

    private String movieTitle;
    private int moviePosition;

    public SimpleDraweeView backDrop;
    private TextView movieText;
    private TextView vote_average;
    private TextView release_date;

    private String posterDetailUrl;
    private int movieId;

    public JsonBean jsonBeanReceived;

    private ArrayList<JsonBean.Results> resultsDetailpage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        movieText = (TextView) findViewById(R.id.overview_text);
        vote_average = (TextView) findViewById(R.id.vote_average_info);
        release_date = (TextView) findViewById(R.id.release_date_info);

        //normal way to get data via intent.putExtra function
        Intent intent = getIntent();
        savedInstanceState = getIntent().getExtras();

        posterDetailUrl = intent.getStringExtra("posterUrltodetailpage");

        //get the jsonBean,results,position from the view of selected movie [Parcelable way]
        jsonBeanReceived = intent.getParcelableExtra("jsonData");
        resultsDetailpage = jsonBeanReceived.getResults();
        moviePosition = savedInstanceState.getInt("position");
        movieTitle = resultsDetailpage.get(moviePosition).getTitle();

        //set the detailed info of movie
        movieText.setText(resultsDetailpage.get(moviePosition).getOverview());
        vote_average.setText("vote_average : " + String.valueOf(resultsDetailpage.get(moviePosition).getVote_average()));
        release_date.setText("release_date : " + resultsDetailpage.get(moviePosition).getRelease_date());

        Log.d("Received jsonData", String.valueOf(jsonBeanReceived));
        Log.d("movieId", String.valueOf(movieId));


        Toolbar toolbarDetail = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout coToolbar = (CollapsingToolbarLayout) findViewById(R.id.coToolbar);
        //Set the title of toolbar
        coToolbar.setTitle(movieTitle);
        //How to set the color and style of title ?

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.favorite_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // store the selected JsonBean Object into SQL

                Toast.makeText(v.getContext(), "Add to My Favorite Movie List", Toast.LENGTH_SHORT).show();
            }
        });

        backDrop = (SimpleDraweeView) findViewById(R.id.detail_backdrop);

        Uri uri = Uri.parse(posterDetailUrl);
        backDrop.setImageURI(uri);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }


}
