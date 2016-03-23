package com.example.agoodob.photogallery;

import android.net.Uri;
import android.util.Log;
import android.widget.Gallery;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class FlickrFetcher {

    public static final String TAG = "FlickrFetcher";
    public static final String PREF_SEARCH_QUERY = "searchQuery";
    public static final String PREF_LAST_RESULT_ID = "lastResultId";


    private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    private static final String API_KEY = "653bfd27f1175690c74dac0d028feb32";
    private static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    private static final String METHOD_SEARCH = "flickr.photos.search";
    private static final String PARA_EXTRAS = "extras";
    private static final String PARA_TEXT = "text";
    // 截止至 2016-3-22 原书代码依然管用

    private static final String EXTRA_SMALL_URL = "url_s";
    private static final String XML_PHOTO = "photo";


    // 返回网络请求的字节流
    byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();

            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();

        } finally {
            conn.disconnect();
        }
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    /*
        拼 url, 拿到请求结果, 解析成我们的对象
     */
    public ArrayList<GalleryItem> downloadGalleryItems(String url){
        ArrayList<GalleryItem> items = new ArrayList<GalleryItem>();

        try{
            String xmlString = getUrl(url);
            Log.i(TAG, "拿到结果了: " + xmlString);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            parseItems(items, parser);

        } catch (Exception e){
            Log.i(TAG, "请求出错: " + e);
        }

        return items;
    }


    public ArrayList<GalleryItem> fetchItems(){
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_GET_RECENT)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter(PARA_EXTRAS, EXTRA_SMALL_URL)
                .build().toString();
        return downloadGalleryItems(url);
    }

    public ArrayList<GalleryItem> search(String query) {
        String url = Uri.parse(ENDPOINT).buildUpon()
                .appendQueryParameter("method", METHOD_SEARCH)
                .appendQueryParameter("api_key", API_KEY)
                .appendQueryParameter(PARA_EXTRAS, EXTRA_SMALL_URL)
                .appendQueryParameter(PARA_TEXT, query)
                .build().toString();
        return downloadGalleryItems(url);
    }

    /*

     */
    void parseItems(ArrayList<GalleryItem> items, XmlPullParser parse)
        throws XmlPullParserException, IOException{

        int eventType = parse.next();

        while(eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG
                    && XML_PHOTO.equals(parse.getName())) {

                String id = parse.getAttributeValue(null, "id");
                String caption = parse.getAttributeValue(null, "title");
                String smallUrl = parse.getAttributeValue(null, EXTRA_SMALL_URL);

                GalleryItem item = new GalleryItem();
                item.setCaption(caption);
                item.setUrl(smallUrl);
                item.setId(id);
                items.add(item);
            }
            eventType = parse.next();
        }
    }

}
