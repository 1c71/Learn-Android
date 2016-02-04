package com.example.agoodob.todo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GetLocat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_locat);
        /*
        * 获取用户位置
        * */

        Button b = (Button) findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager LM = (LocationManager) GetLocat.this.getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(GetLocat.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GetLocat.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(GetLocat.this, "无法获取权限, 定位失败", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(GetLocat.this, "运行到这里了吗", Toast.LENGTH_SHORT).show();
                    LM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationList());
                }
            }
        });


    }


    private class LocationList implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            location.getLatitude();
            location.getLongitude();
            Toast.makeText(GetLocat.this, "LocationChanged", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(GetLocat.this, "onStatusChanged", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(GetLocat.this, "onProviderEnabled", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(GetLocat.this, "onProviderDisabled", Toast.LENGTH_LONG).show();
        }
    }


}
