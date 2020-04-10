package com.alex.winemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.WineAdmin;

public class RateMeActivity extends AppCompatActivity {
    private WineAdmin wineAdmin= WineAdmin.getInstance();
    private RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_me);
        setTitle("Rate me!");

        ratingBar = findViewById(R.id.ratingBarRateMe);

    }

    public void submitRating(View view) { //Add rating to ratings

        wineAdmin.newRating(ratingBar);
        Toast.makeText(this, R.string.rating_send, Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    }
}
