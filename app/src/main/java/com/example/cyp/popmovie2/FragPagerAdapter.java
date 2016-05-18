package com.example.cyp.popmovie2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by apple on 16/5/5.
 */

//http://api.themoviedb.org/3/movie/id?api_key=my_api_key
//Custom FragmentPagerAdapter for adding fragments
public class FragPagerAdapter extends FragmentPagerAdapter {

    private String popApi = "http://api.themoviedb.org/3/movie/popular";
    //the base api of toprated
    private String topRatedAPi = "http://api.themoviedb.org/3/movie/top_rated";
    private String[] titleList = {"Most Popular", "Top Rated", "My Favorite"};
    private List<String> mFragmentTitles = new ArrayList<>(Arrays.asList(titleList));
//    private List<Fragment> mFragments = new ArrayList<>();

    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

//    public void addFragment(Fragment fragment, String title) {
////        mFragments.add(fragment);
//        mFragmentTitles.add(title);
//    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MovieListFragment.newInstance(popApi);
            case 1:
                return MovieListFragment.newInstance(topRatedAPi);
            case 2:
                //Favorite Tab
                return MovieListFragment.newInstance(topRatedAPi);
//                return FavoriteFragment.newInstance();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mFragmentTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }



}