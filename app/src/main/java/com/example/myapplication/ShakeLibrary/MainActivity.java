package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ShakeListener mShakeListener = null;
    TextView tv ;
    Button reshake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reshake = (Button) findViewById(R.id.shake);
        tv = (TextView) findViewById(R.id.textview);
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new shakeLitener());
        reshake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("摇起来");
                mShakeListener.start();
            }
        });
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
}