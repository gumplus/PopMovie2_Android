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
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends AppCompatActivity  {

    private DrawerLayout mDrawerLayout;
    // you can enter your api_key below:
    private final String api_key = "?api_key=92741aee53714cbe1a7d87fc658bbaad";
    //the base api of popular
    private String popApi = "http://api.themoviedb.org/3/movie/popular";
    //the base api of toprated
    private String topRatedAPi = "http://api.themoviedb.org/3/movie/top_rated";

    //https://api.themoviedb.org/3movie?api_key=API_KEY&page=2

//    public DatabaseHelper dbHelper;


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



    private void setupViewPager(ViewPager viewpager) {

        FragPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager());

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
