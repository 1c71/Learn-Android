package com.example.agoodob.crashcourse;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by agoodob on 2016/3/23.
 */
public class MainFragment extends ListFragment {

    ListView lv;
    ArrayList<Episode> episodes = new ArrayList<Episode>();

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Episode e = new Episode("a", "b", "c", "d");
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);

//        lv = (ListView) v.findViewById(R.id.episode);
//        lv.setAdapter(new MyAdapter(getActivity(), episodes));
//
        return v;
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

//            ImageView imageView = (ImageView)
//                    convertView.findViewById(R.id.gallery_item_imageView);
//            imageView.setImageResource(R.drawable.brian_up_close);
//            GalleryItem item = getItem(position);
//            mThumbnailThread.queueThumbnail(imageView, item.getUrl());

            return convertView;
        }
    }
}
