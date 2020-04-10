package com.alex.winemanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.View.PieChartView;
import com.alex.winemanager.model.Country;
import com.alex.winemanager.model.WineAdmin;

import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChartView pieChartView = findViewById(R.id.pieChart);
        float[] pieValues = getCountryWineNumber();
        pieChartView.setDataPoints(pieValues); // Draw the chart based on the countries and their wines
    }

    private float[] getCountryWineNumber(){ // Counts and add number of wines of each country to pieChartValues
        List<Country> countries = wineAdmin.getCountryList();
        float[] pieChartValues = new float[countries.size()];
        int floatIndex = 0;
        for (int i = 0; i < countries.size() ; i++) {
            pieChartValues[floatIndex] = countries.get(i).getWineList().size(); //Add how many wines has that country
            floatIndex++;
        }
        return pieChartValues;
    }
}
