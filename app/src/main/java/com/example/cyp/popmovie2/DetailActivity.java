package com.example.cyp.popmovie2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apple on 16/5/5.
 */
public class DetailActivity extends AppCompatActivity {

    private static String videoApi = "http://api.themoviedb.org/3/movie/";
    private static final String video_api_key = "/videos?api_key=92741aee53714cbe1a7d87fc658bbaad";

    // videoApi+id+video_api_key
    // https://www.youtube.com/watch?v=videoKey
    //videoKey = videoBean.getResults().getKey();

    private String movieTitle;
    private int moviePosition;
    private String videoApiUrl;


    public SimpleDraweeView backDrop;
    private TextView movieText;
    private TextView vote_average;
    private TextView release_date;
    private ImageView trailerPlay;

    private String posterDetailUrl;
    private int movieId;

    private OkHttpClient okHttpClient = new OkHttpClient();
    private Gson gsonDetail = new Gson();
    private JsonBean BeanReceived;

    private VideoBean videoBean = new VideoBean();
    private ArrayList<JsonBean.Results> resultsDetailpage;
    private ArrayList<VideoBean.VideoResults> resultsVideo;
    private String videoKey;
    private String playUrl ;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        movieText = (TextView) findViewById(R.id.overview_text);
        vote_average = (TextView) findViewById(R.id.vote_average_info);
        release_date = (TextView) findViewById(R.id.release_date_info);
        trailerPlay = (ImageView) findViewById(R.id.trailer_play);

        dbHelper = new DatabaseHelper(this, "MovieId.db", null, 1);
        resultsDetailpage = new ArrayList<>();
        resultsVideo = new ArrayList<>();

        //normal way to get data via intent.putExtra function
        Intent intent = getIntent();
        savedInstanceState = getIntent().getExtras();

        posterDetailUrl = intent.getStringExtra("posterUrltodetailpage");

        //get the jsonBean,results,position from the view of selected movie [Parcelable way]
        BeanReceived = intent.getParcelableExtra("jsonData");
        resultsDetailpage = BeanReceived.getResults();

        moviePosition = savedInstanceState.getInt("position");

        movieTitle = resultsDetailpage.get(moviePosition).getTitle();
        movieId = resultsDetailpage.get(moviePosition).getId();
        //set the detailed info of movie
        movieText.setText(resultsDetailpage.get(moviePosition).getOverview());
        vote_average.setText("vote_average : " + String.valueOf(resultsDetailpage.get(moviePosition).getVote_average()));
        release_date.setText("release_date : " + resultsDetailpage.get(moviePosition).getRelease_date());

        Log.d("Received jsonData", String.valueOf(BeanReceived));
        Log.d("movieId", String.valueOf(movieId));


        Toolbar toolbarDetail = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout coToolbar = (CollapsingToolbarLayout) findViewById(R.id.coToolbar);
        //Set the title of toolbar
        coToolbar.setTitle(movieTitle);
        //How to set the color and style of title ?
        backDrop = (SimpleDraweeView) findViewById(R.id.detail_backdrop);

        Uri uri = Uri.parse(posterDetailUrl);
        backDrop.setImageURI(uri);

        trailerPlay();

        dbHelper = new DatabaseHelper(this, "Movie.db", null, 1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.favorite_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add to My Favorite Tab

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (dbHelper.deleteMovie(movieId) == 0) {
                    Log.d("Delete Failed", "!");

                    ContentValues values = new ContentValues();
                    values.put("movieId", movieId);
                    values.put("posterUrl", posterDetailUrl);
                    values.put("title", movieTitle);
                    values.put("overview", resultsDetailpage.get(moviePosition).getOverview());
                    values.put("vote_average", resultsDetailpage.get(moviePosition).getVote_average());
                    values.put("release_date", resultsDetailpage.get(moviePosition).getRelease_date());

                    db.insert("Movie", null, values);
                    Toast.makeText(v.getContext(), "Collect the movie: " + movieTitle, Toast.LENGTH_SHORT).show();

                } else {
                    dbHelper.deleteMovie(movieId);
                    Toast.makeText(v.getContext(), "Delete the movie: " + movieTitle, Toast.LENGTH_SHORT).show();
                }



            }

        });

    }

    private void trailerPlay() {
        // videoApi+id+video_api_key
        videoApiUrl = videoApi + movieId + video_api_key;

        Request requestTrailer = new Request.Builder().url(videoApiUrl).build();

        okHttpClient.newCall(requestTrailer).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    if(!response.isSuccessful()) throw new IOException("Wrong Response for Trailer Play" + response);
                    videoBean = gsonDetail.fromJson(response.body().string(), VideoBean.class);
                    resultsVideo = videoBean.getResults();
                    videoKey = resultsVideo.get(0).getKey();
                    Log.d("videoKey is ", videoKey);
                    playUrl = "https://www.youtube.com/watch?v=" + videoKey;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        trailerPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(Intent.ACTION_VIEW);
                intentPlay.setData(Uri.parse(playUrl));
                startActivity(intentPlay);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }


}
