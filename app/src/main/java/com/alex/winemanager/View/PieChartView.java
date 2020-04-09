package com.alex.winemanager.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieChartView extends View {


    private Paint slicePaint;
    private List<Integer> sliceColors;
    private RectF rectF;
    private float[] values; // Values
    private Random rnd;

    public PieChartView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        sliceColors = new ArrayList<>();
        slicePaint = new Paint();
        slicePaint.setAntiAlias(true);
        slicePaint.setDither(true);
        slicePaint.setStyle(Paint.Style.FILL);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {


        if(this.values != null) {
            int startTop = 0;
            int startLeft = 0;
            int endBottom = getHeight();
            int endRight = endBottom; //To make this an equal square

            rectF = new RectF(startLeft, startTop, endRight, endBottom); //Param drawArc
            float[] scaledValues = scale(); //Get the scaled values
            float sliceStartPoint = 0;
            rnd = new Random();
            for (int i = 0; i < scaledValues.length; i++) {
                slicePaint.setColor(checkColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))));
                canvas.drawArc(rectF, sliceStartPoint, scaledValues[i], true, slicePaint); //Draw slice
                sliceStartPoint += scaledValues[i]; //Update starting point of the next slice
            }
        }
    }
 /*
 * @param Color as integer
 * @return Color check if not used before
 *
 * */
    private int checkColor(int argb) {
        if (!sliceColors.contains(argb)){
            sliceColors.add(argb);
            return argb;
        }else{
            rnd = new Random();
            argb = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            sliceColors.add(argb);
            return argb;
        }
    }

    public void setDataPoints(float[] values) {
        this.values = values;
        invalidate(); //Tells the chart to redraw itself
    }

    private float[] scale() {
        float[] scaledValues = new float[this.values.length];
        float total = getTotal(); //Total all values supplied to the chart
        for (int i = 0; i < this.values.length; i++) {
            scaledValues[i] = (this.values[i] / total) * 360; //Scale each value
        }
        return scaledValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.values)
            total += val;
        return total;
    }

}
