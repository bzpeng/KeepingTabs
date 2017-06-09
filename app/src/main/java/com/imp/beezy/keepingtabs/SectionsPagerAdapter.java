package com.imp.beezy.keepingtabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by bpeng on 2017-05-04.
 */

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch(position) {
            case 0:
                fragment = PlaceholderFragment.newInstance(1);
                return fragment;
            case 1:
                fragment = GoalsFragment.newInstance();
                return fragment;
            default:
                return PlaceholderFragment.newInstance(1);
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
        }
        return null;
    }
}
