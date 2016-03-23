package com.example.agoodob.locationget;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

/**
 * 2016-3-2
 * 获取位置信息的 DEMO
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> l = lm.getAllProviders(); // 获取所有的位置提供者

        lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, new MyLocationListener());
        // 位置提供者，最短时间，最短距离，监听器

    }

    class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            String j = "纬度:" + location.getLongitude();
            String w = "经度: " + location.getLatitude();
            String accuracy = "精确度: " + location.getAccuracy();
            String haiba = "海拔:" + location.getAltitude();
        }

        // provider 的状态发生变化时调用
        // 比如从不能用变得能用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
