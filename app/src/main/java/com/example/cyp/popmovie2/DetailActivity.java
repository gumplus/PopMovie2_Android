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

/**
 * Created by apple on 16/5/5.
 */
public class DetailActivity extends AppCompatActivity {

    public SimpleDraweeView detailPostView;
    public SimpleDraweeView backDrop;
    public TextView movieText;
    private String posterDetailUrl;
    private int movieId;

    public JsonBean jsonBeanReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        movieText = (TextView) findViewById(R.id.movie_text);


        Toolbar toolbarDetail = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout coToolbar = (CollapsingToolbarLayout) findViewById(R.id.coToolbar);
        coToolbar.setTitle("电影详情页");

        detailPostView = (SimpleDraweeView) findViewById(R.id.detail_fresco);
        backDrop = (SimpleDraweeView) findViewById(R.id.detail_backdrop);

        Intent intent = getIntent();
        posterDetailUrl = intent.getStringExtra("posterUrltodetailpage");
        movieId = intent.getIntExtra("movie_id_todetailpage",1);

        jsonBeanReceived = intent.getParcelableExtra("jsonData");

        Log.d("Received jsonData", String.valueOf(jsonBeanReceived));
        Log.d("movieId", String.valueOf(movieId));

        Uri uri = Uri.parse(posterDetailUrl);
//        detailPostView.setImageURI(uri);
        backDrop.setImageURI(uri);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }


}
