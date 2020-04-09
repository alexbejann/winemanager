package com.alex.winemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.WineAdmin;

public class EditCountryActivity extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private EditText country;
    private ImageButton saveButton;
    private String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country);
        country = findViewById(R.id.editCountryName);
        saveButton = findViewById(R.id.saveButtonCountry);
        Intent intent = getIntent();
        countryName  = intent.getStringExtra("country");
        country.setText(countryName);
    }


    public void saveCountryName(View view) {
        if (!country.getText().toString().isEmpty()){
            wineAdmin.getCountryByName(countryName).setCountryName(country.getText().toString());

            finish();
        }

    }
}
