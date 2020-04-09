package com.alex.winemanager.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.alex.winemanager.R;

public class AppReviewView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();


    private boolean niceRatings = false;

    public AppReviewView(Context context) {
        super(context);
    }

    public AppReviewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AppReviewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AppReviewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void onDraw(Canvas canvas) {

        float width = getWidth();
        float height = getHeight();
        float min = Math.min(width, height);
        float fat = min / 17;

        paint.setStrokeWidth(fat);
        paint.setStyle(Paint.Style.STROKE);

        paint.setAntiAlias(true);

        if (niceRatings) { //heart

            path.moveTo(width / 2, height / 5);


            path.cubicTo(5 * width / 14, 0,
                    0, height / 15,
                    width / 28, 2 * height / 5);


            path.cubicTo(width / 14, 2 * height / 3,
                    3 * width / 7, 5 * height / 6,
                    width / 2, height);


            path.cubicTo(4 * width / 7, 5 * height / 6,
                    13 * width / 14, 2 * height / 3,
                    27 * width / 28, 2 * height / 5);


            path.cubicTo(width, height / 15,
                    9 * width / 14, 0,
                    width / 2, height / 5);

            paint.setStyle(Paint.Style.FILL);

            canvas.drawPath(path, paint);



        } else {
            canvas.drawLine(0, 0,width, height, paint);
            canvas.drawLine(width, 0, 0, height, paint);

        }
    }

    public void setAppReview(float averageRating) {

        if (averageRating > 3) {
            niceRatings = true;
            paint.setColor(getResources().getColor(R.color.colorAccent));
        } else {
            paint.setColor(getResources().getColor(R.color.colorGray));
        }
    }
}
