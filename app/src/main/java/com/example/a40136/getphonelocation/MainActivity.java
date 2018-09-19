package com.example.a40136.getphonelocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    private LocationManager locationManager;
    private String provider;
    private String locationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //获取当前可用的位置控制器
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        }
        else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(this, "请检查网络或GPS是否打开",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            //获取当前位置，这里只用到了经纬度
            locationInfo = "纬度为：" + location.getLongitude() + ",经度为："
                    + location.getLatitude();
            Toast.makeText(this,"已经赋值",Toast.LENGTH_SHORT).show();
        }


    }


    private void initUI(){

        button=(Button)findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.location);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textView.setText(locationInfo);
        Toast.makeText(this,locationInfo,Toast.LENGTH_LONG).show();
    }
}
