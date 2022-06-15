package com.example.nbhwarehouse;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen_Activity.this, HomeMenu_Activity.class);
                startActivity(i);
            }
        }, 1000);

    }
}