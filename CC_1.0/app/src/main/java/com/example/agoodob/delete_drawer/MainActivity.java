package com.example.agoodob.delete_drawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "MainActivity";
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        try {
            run();
//            String r = run("http://www.baidu.com");
//            Log.i(TAG, r);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        ArrayList<Episode> episodes = new ArrayList<Episode>();
        ArrayList<Series> series = new ArrayList<Series>();
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static int sectionNum;

        public PlaceholderFragment() {


            Episode e = new Episode("a", "b", "c", "d");
            episodes.add(e);
            episodes.add(e);
            episodes.add(e);
            episodes.add(e);
            episodes.add(e);



            for (int i = 0; i < 17; i++) {
                Series s = new Series("3", "标题", "http:/.www");
                series.add(s);
            }

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            sectionNum = sectionNumber;
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int page = getArguments().getInt(ARG_SECTION_NUMBER);

            if (page == 1){
                View rootView = inflater.inflate(R.layout.activity_newest, container, false);
                ListView lv = (ListView) rootView.findViewById(R.id.episode);
                lv.setAdapter(new MyAdapter(getActivity(), episodes));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(getActivity(), "asd", Toast.LENGTH_SHORT).show();

//                        Intent i = new Intent(getActivity(), SlidingExample.class);
//                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
//                Intent i = new Intent(MainActivity.this, VideoActivity.class);
//                        startActivity(i);

                    }
                });
                return rootView;
            } else if (page == 2){
                View rootView = inflater.inflate(R.layout.activity_series, container, false);
                GridView mGridView = (GridView) rootView.findViewById(R.id.series);
                mGridView.setAdapter(new SeriesAdapter(getActivity(), series));
                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getActivity(), SeriesActivity.class);
                        startActivity(i);
                    }
                });
                return rootView;
            } else {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }

        }


        private class MyAdapter extends ArrayAdapter<Episode> {

            public MyAdapter(Context c ,ArrayList<Episode> items) {
                super(c, 0, items);
                // super 的最后一个 items 参数如果不传就不会显示任何东西
            }
            @Override
            public int getCount() {
                return episodes.size();
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if(convertView == null){
                    convertView = getActivity().getLayoutInflater()
                            .inflate(R.layout.list_item, parent, false);
                }

                return convertView;
            }
        }

        private class SeriesAdapter extends ArrayAdapter<Series> {

            public SeriesAdapter(Context c ,ArrayList<Series> items) {
                super(c, 0, items);
                // super 的最后一个 items 参数如果不传就不会显示任何东西
            }
            @Override
            public int getCount() {
                return series.size();
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {


                if(convertView == null){
                    convertView = getActivity().getLayoutInflater()
                            .inflate(R.layout.grid_item, parent, false);
                }

//            ImageView imageView = (ImageView)
//                    convertView.findViewById(R.id.gallery_item_imageView);
//            imageView.setImageResource(R.drawable.brian_up_close);
//            GalleryItem item = getItem(position);
//            mThumbnailThread.queueThumbnail(imageView, item.getUrl());

                return convertView;
            }
        }

    }

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
            return PlaceholderFragment.newInstance(position + 1);
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
                    return "最新";
                case 1:
                    return "系列";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

}
