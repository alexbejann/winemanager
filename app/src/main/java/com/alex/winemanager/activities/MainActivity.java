package com.alex.winemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;


public class MainActivity extends AppCompatActivity {

    private Boolean signupModeActive = false;
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, CountriesActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
        //Set splash screen image
        ImageView imageView = findViewById(R.id.splash);
        imageView.setImageResource(R.drawable.spash);
        setTitle("Wine Manager");
    }
}
