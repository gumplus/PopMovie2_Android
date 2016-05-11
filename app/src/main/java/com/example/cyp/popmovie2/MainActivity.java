package com.example.cyp.popmovie2;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    private final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";

    //the base api of popular
    private String popBaseApi = "http://api.themoviedb.org/3/movie/popular";
    //the base api of toprated
    private String ratedBaseAPi = "http://api.themoviedb.org/3/movie/top_rated";
    //the base part api of absolutePath of movie poster
    private String posterBaseUrl = "http://image.tmdb.org/t/p/w185";

    //store the
    private String popPosterPath;
    //the complete absolute path of a movie poster
    private String popFinalUrl;
    //a collection of absolute url of movie poster
    private ArrayList<String> posterUrls = new ArrayList<>();
    //Movie Id
    private ArrayList<Integer> movieIdList = new ArrayList<>();

    public Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //get movie poster urls
        try {
            getPosterUrls();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //normal way to store detailed data
        bundle.putStringArrayList("movie_urls", posterUrls);
        bundle.putIntegerArrayList("movie_id", movieIdList);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if(navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        if(viewpager != null) {
            setupViewPager(viewpager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewpager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // "android" here is demanded? why?
            case android.R.id.home:
                //rebuild the drawerLayout  ?
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }


    public void getPosterUrls() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();


        //这里需要 识别区分 查询api （pop or toprated）
        Request request = new Request.Builder()
                .url(popBaseApi + api_key)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(!response.isSuccessful()) throw new IOException("Wrong Code" + response);

                Gson gson = new Gson();
                JsonBean jsonBean = new JsonBean();

                try {
                    jsonBean = gson.fromJson(response.body().string(), JsonBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                ArrayList<JsonBean.Results> result = jsonBean.getResults();

                //把jsonbean  和  result Object直接传递到 bundle 存储
                bundle.putParcelable("jsonbean_key", jsonBean);

                Log.d("result.size", String.valueOf(result.size()));

                for (int i =0; i< result.size();i++) {

                    //get poster path:
                    popPosterPath = result.get(i).getPoster_path();
                    Log.d("popPosterPath", popPosterPath);

                    //get movie id list:
                    Log.d("result.getId()", String.valueOf(result.get(i).getId()));
                    movieIdList.add(result.get(i).getId());

                    popFinalUrl = posterBaseUrl + popPosterPath;
                    Log.d("popFinalUrl", popFinalUrl);

                    posterUrls.add(popFinalUrl);
                    Log.d("posterUrls",  posterUrls.toString());

                }
            }
        });

    }

    private void setupViewPager(ViewPager viewpager) {

        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager());


        // add fragment into adpter
        adapter.addFragment(MovieListFragment.newInstance(bundle), "Most Popular");
        adapter.addFragment(MovieListFragment.newInstance(bundle), "Top Rated");
        adapter.addFragment(MovieListFragment.newInstance(bundle), "My Favorite");

        viewpager.setAdapter(adapter);
    }

    //setNavigationItem SelectedListener

    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        //closeDrawers of the touched view?
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );

    }


}
