package com.alex.winemanager.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Country;
import com.alex.winemanager.model.WineAdmin;

public class AddCountryActivity extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private ImageButton saveButton;
    private EditText countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setEnabled(false);
        countryName = findViewById(R.id.countryName);

        //Add textWatcher to notify the user
        countryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Before user enters the text
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //On user changes the text
                if(s.toString().trim().length()==0) {
                    saveButton.setEnabled(false);
                } else {
                    saveButton.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //After user is done entering the text
            }
        });

    }

    public void createCountry(View view) { // Create Country

            Country country = new Country(countryName.getText().toString());
            wineAdmin.addCountry(country);
            finish();


    }
}
