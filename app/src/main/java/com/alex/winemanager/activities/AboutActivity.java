package com.alex.winemanager.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.View.AppReviewView;
import com.alex.winemanager.View.AverageRatingView;
import com.alex.winemanager.model.WineAdmin;

public class AboutActivity extends AppCompatActivity {
    private WineAdmin wineAdmin = WineAdmin.getInstance();
    AverageRatingView AverageRatingView;
    AppReviewView AppReviewView;
    TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle(getString(R.string.aboutManager));

        AverageRatingView = findViewById(R.id.AverageRatingView);
        AppReviewView = findViewById(R.id.AppReviewView);
        aboutTextView = findViewById(R.id.aboutTextView);
        //Set rating
        AverageRatingView.setAverageRating(wineAdmin.getNumberOfRatings(), wineAdmin.getAverageRating());
        AppReviewView.setAppReview(wineAdmin.getAverageRating());

        if (wineAdmin.getNumberOfRatings() > 3) {

           aboutTextView.setText(getString(R.string.rating));

        } else {

            aboutTextView.setText(getString(R.string.no_ratings));

        }

    }
}
