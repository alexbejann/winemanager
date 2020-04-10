package com.alex.winemanager.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alex.winemanager.R;

public class AverageRatingView extends LinearLayout {

    private RatingBar ratingBar;
    private TextView textView;

    public AverageRatingView(Context context) {
        super(context);
        init();
    }

    public AverageRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AverageRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AverageRatingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {

        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        layoutInflater.inflate(R.layout.averageratingview, this);

        ratingBar = findViewById(R.id.myRatingBar);
        textView = findViewById(R.id.ratingTextView);

    }

    public void setAverageRating(int numberOfRatings, float average) { // Set ratings after average

        if (numberOfRatings > 1) {

            if (average == 1 || average == 2 || average == 3 || average == 4 || average == 5) {
                String text =   getResources().getString(R.string.rating_)+ " " + (int) average ;
                textView.setText(text);
            } else {
                String text =   getResources().getString(R.string.rating_)+ " " + average ;
                textView.setText(text);
            }
            ratingBar.setRating(average);

        } else {
            textView.setText(getResources().getString(R.string.no_ratings));
        }

    }
}
