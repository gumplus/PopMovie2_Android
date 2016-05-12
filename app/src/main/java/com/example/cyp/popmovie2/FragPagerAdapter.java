package com.example.cyp.popmovie2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/5/5.
 */

//Custom FragmentPagerAdapter for adding fragments
public class FragPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();
//    private static int NUM_ITEMS = 3;


    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return MovieListFragment.newInstance(bundle);
//            case 1:
//                return MovieListFragment.newInstance(bundle);
//            case 2:
//                return MovieListFragment.newInstance(bundle);
//            default:
//                return null;
//        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
//        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}