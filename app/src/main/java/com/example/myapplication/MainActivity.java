package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ShakeListener mShakeListener = null;
    TemperatureListener mTemperatureListener = null;
    LightListener mLightListener = null;

    TextView tv ;
    TextView temperatureTV;
    TextView lightTV;
    Button reshake;
    TextView temperaturetextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reshake = (Button) findViewById(R.id.shake);
        tv = (TextView) findViewById(R.id.textview);
        lightTV = (TextView) findViewById(R.id.lightTV);
        temperaturetextView = (TextView) findViewById(R.id.temperaturetextView);


        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new shakeLitener());

        mTemperatureListener = new TemperatureListener(this);
        mTemperatureListener.setOnTemperatureListener(new temperatureLitener());

        mLightListener = new LightListener(this);
        mLightListener.setOnLightListener(new lightChangeListener());

        reshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("摇起来");
                mShakeListener.start();
            }
        });



    }

//get the screen light
    private int getsystemlight(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, 255);
    }
    private void setApplight(int lightvalue) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutparams = window.getAttributes();
        layoutparams.screenBrightness = lightvalue/ 255.0f;
        window.setAttributes(layoutparams );
    }




    private class shakeLitener implements ShakeListener.OnShakeListener {
        @Override
        public void AfterShake() {
            // TODO Auto-generated method stub
            tv.setText("摇一摇成功啦！");
            mShakeListener.stop();
        }
        public void StartShake() {
            // TODO Auto-generated method stub
            tv.setText("开始摇一摇");
        }

        public void OnShaking() {
            // TODO Auto-generated method stub
            tv.setText("摇一摇中！");
        }
    }

    private class temperatureLitener implements TemperatureListener.TemperatureChangeListener {
        @Override
        public void ChangeTemperature(float temp) {
            temperaturetextView.setText("temperature:" + temp + "℃");
        }
    }


    private class lightChangeListener implements LightListener.LightChangeListener{

        @Override
        public void ChangeLight(SensorEvent temp) {
            float acc = temp.accuracy;
            float lux = temp.values[0];
            lightTV.setText("acc:"+acc+";"+"lux："+lux);
            setApplight((int)(lux*1.8));




        }
    }
}