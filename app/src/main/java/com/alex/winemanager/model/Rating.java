package com.alex.winemanager.model;

import android.widget.RatingBar;

public class Rating {

    private float rating;

    public Rating(float rating) {
        this.rating = rating;
    }

    public Rating(RatingBar ratingBar) {
        rating = ratingBar.getRating();
    }

    public double getRating() {
        return rating;
    }

}
