package com.example.agoodob.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = new DummySectionFragment();
        Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    // Section 的数量
    //
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    // Section 的标题
    // CharSequence  getPageTitle
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Message";
            case 1:
                return "My Friend";
        }
        return null;
    }
}

