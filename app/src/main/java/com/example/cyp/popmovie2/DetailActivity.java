package com.example.cyp.popmovie2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by apple on 16/5/5.
 */
public class DetailActivity extends AppCompatActivity {

    private String movieTitle;
    private int moviePosition;

    public SimpleDraweeView backDrop;
    public TextView movieText;
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

        //normal way to get data via intent.putExtra function
        Intent intent = getIntent();
        savedInstanceState = getIntent().getExtras();

        posterDetailUrl = intent.getStringExtra("posterUrltodetailpage");

        //get the jsonBean,results,position from the view of selected movie [Parcelable way]
        jsonBeanReceived = intent.getParcelableExtra("jsonData");
        resultsDetailpage = jsonBeanReceived.getResults();
        moviePosition = savedInstanceState.getInt("position");
        movieTitle = resultsDetailpage.get(moviePosition).getTitle();

        Log.d("Received jsonData", String.valueOf(jsonBeanReceived));
        Log.d("movieId", String.valueOf(movieId));




        Toolbar toolbarDetail = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout coToolbar = (CollapsingToolbarLayout) findViewById(R.id.coToolbar);
        //Set the title of toolbar
        coToolbar.setTitle(movieTitle);
        //How to set the color and style of title ?

//        detailPostView = (SimpleDraweeView) findViewById(R.id.detail_fresco);
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
