package com.alex.winemanager.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

public class EditWineActivity extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private ImageView winePicture;
    private EditText nameEdit;
    private EditText grapeEdit;
    private EditText regionEdit;
    private EditText countryEdit;
    private TextView yearEdit;
    private EditText descriptionEdit;
    private Wine wine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wine);

        setTitle("Edit Wine");

        Intent intent=getIntent();
        String wineName =intent.getStringExtra("selectedWine");
        wine = wineAdmin.getWineByName(wineName);
        nameEdit = findViewById(R.id.nameEdit);
        grapeEdit = findViewById(R.id.grapesEdit);
        regionEdit = findViewById(R.id.regionEdit);
        countryEdit = findViewById(R.id.countryEdit);
        yearEdit = findViewById(R.id.prodYear);
        descriptionEdit = findViewById(R.id.descriptionEdit);
        winePicture = findViewById(R.id.editPicture);
        Log.i("edit",wine.getYear()+"");
        nameEdit.setText(wine.getName());
        grapeEdit.setText(wine.getGrapes());
        regionEdit.setText(wine.getRegion());
        countryEdit.setText(wine.getCountry());
        yearEdit.setText(wine.getYear()+"");
        descriptionEdit.setText(wine.getDescription());
        Log.i("wineEdit", getImageId(this, wine.getPicture())+"");
        winePicture.setImageResource(getImageId(this, wine.getPicture()));




    }
    public static int getImageId(Context context, String imageName) { // Get Image ID to set resource
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public void saveChanges(View view) { // Save Button
        if(wine != null){
            wine.setName(nameEdit.getText().toString().toUpperCase());
            wine.setCountry(countryEdit.getText().toString());
            wine.setGrapes(grapeEdit.getText().toString());
            wine.setRegion(regionEdit.getText().toString());
            wine.setDescription(descriptionEdit.getText().toString());
            wine.setPicture(wine.getPicture());
            wineAdmin.removeWine(wine); // If country changed remove from frist country
            wineAdmin.addWineObject(wine); // Add wine to the new country

            finish();
        }

    }
}
