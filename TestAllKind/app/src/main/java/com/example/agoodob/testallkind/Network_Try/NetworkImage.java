package com.example.agoodob.testallkind.Network_Try;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agoodob.testallkind.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 2016-2-24
 * 点击按钮之后从网络上下载一张图片并显示在界面上
 *
 * 1 设置好图片路径
 * 2 URL 对象
 * 3 调用 URL 对象的 openConnection 得到一个 HttpURLConnection 对象
 * 4 设置 GET 还是 POST
 * 5 连接超时
 * 6 连接后超时
 * 7 判断响应码
 * 8 成功后弄成 Bitmap
 * 9
 */
public class NetworkImage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image);
        Button networkImageButton = (Button) findViewById(R.id.networkImageButton);
        networkImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 下载图片
        String imagePath = "adfadfaf";

        try {
            URL url = new URL(imagePath);
            // 获取连接对象, 此时还没有建立连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000); // 设置连接超时
            conn.setReadTimeout(3000); // 如果服务器迟迟不传递数据
            conn.connect();

            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                Bitmap bm = BitmapFactory.decodeStream(is);
                ImageView imageView = (ImageView) findViewById(R.id.networkImageView);
                imageView.setImageBitmap(bm);
            } else {
                Toast.makeText(NetworkImage.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
