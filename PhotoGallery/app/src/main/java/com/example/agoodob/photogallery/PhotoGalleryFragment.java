package com.example.agoodob.photogallery;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/*




 */
public class PhotoGalleryFragment extends Fragment {

    public static final String TAG = "PhotoGalleryFragment";

    GridView mGridView;
    ArrayList<GalleryItem> mItems;
    ThumbnailDownloader<ImageView> mThumbnailThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        new FetchItemsTask().execute();

        mThumbnailThread = new ThumbnailDownloader<ImageView>(new Handler());
        mThumbnailThread.setListener(new ThumbnailDownloader.Listener<ImageView>() {
            @Override
            public void onThumbnailDownloaded(ImageView imageView, Bitmap thumbnail) {
                if(isVisible()) {
                    imageView.setImageBitmap(thumbnail);
                }
            }
        });
        mThumbnailThread.start();
        mThumbnailThread.getLooper();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_photo_gallery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_search:
                getActivity().onSearchRequested();
                return true;
            case R.id.menu_item_clear:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mGridView = (GridView) v.findViewById(R.id.gridView);

        setupAdapter();

        return v;
    }

    private void setupAdapter() {
        if(getActivity() == null || mGridView == null) return;

        if(mItems != null){
            Log.i(TAG, "设置了---------");
            mGridView.setAdapter(new GalleryItemAdapter(mItems));
        } else {
            Log.i(TAG, "没设置。。。。。。");
            mGridView.setAdapter(null);
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<GalleryItem>> {
        // 第3个泛型参数是  返回结果的数据类型  它设置了doInBackground(...)方法返回结果的类型，
        // 以及onPostExecute(...)方法输入参数的数据类型。

        // 第1个是输入参数的类型

        // 第2个类型参数可指定发送进度更新需要的类型

        // p407 《Android编程权威指南》有例子
        @Override
        protected ArrayList<GalleryItem>
            doInBackground(Void... params) {

            String query = "android";
            if(query!=null){
                return new FlickrFetcher().search(query);
            } else {
                return new FlickrFetcher().fetchItems();
            }
        }

        /*
        onPostExecute 会在 doInBackground 之后执行
        而且会在主线程上执行
         */
        @Override
        protected void onPostExecute(ArrayList<GalleryItem> galleryItems) {
            mItems = galleryItems;
            setupAdapter();
        }
    }


    private class GalleryItemAdapter extends ArrayAdapter<GalleryItem>{

        public GalleryItemAdapter(ArrayList<GalleryItem> items) {
            super(getActivity(), 0, items);
            // super 的最后一个 items 参数如果不传就不会显示任何东西
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = getActivity()
                        .getLayoutInflater()
                        .inflate(R.layout.gallery_item, parent, false);
            }

            ImageView imageView = (ImageView)
                    convertView.findViewById(R.id.gallery_item_imageView);
            imageView.setImageResource(R.drawable.brian_up_close);
            GalleryItem item = getItem(position);
            mThumbnailThread.queueThumbnail(imageView, item.getUrl());

            return convertView;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailThread.quit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailThread.clearQueue();
    }
}
