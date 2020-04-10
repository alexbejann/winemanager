package com.alex.winemanager.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

import java.io.File;

public class WineProfile extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private ImageView imageView;
    private TextView name;
    private TextView year;
    private TextView grapes;
    private TextView country;
    private TextView region;
    private TextView description;
    private String wineEditText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wine_profile);
        setTitle("Wine");
        Intent intent = getIntent();
        String nameWine = intent.getStringExtra("selectedWine");
        Wine wine = wineAdmin.getWineByName(nameWine);
        wineEditText = nameWine;


        imageView = findViewById(R.id.wineImage);

        if(!wine.getPicture().contains("data")){

            Log.i("imageDisplay", wine.getPicture());
            imageView.setImageResource(getImageId(this, wine.getPicture()));

        }else{

            imageView.setImageURI(Uri.fromFile(new File(wine.getPicture())));

        }


        name = findViewById(R.id.name);
        name.setText(wine.getName());

        year = findViewById(R.id.year);
        year.setText(wine.getYear()+"");

        grapes = findViewById(R.id.grapes);
        grapes.setText(wine.getGrapes());

        country = findViewById(R.id.country);
        country.setText(wine.getCountry());

        region = findViewById(R.id.region);
        region.setText(wine.getRegion());

        description = findViewById(R.id.description);
        description.setText(wine.getDescription());

    }

    public static int getImageId(Context context, String imageName) { // Get Image ID to set resource

        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());

    }

    public void editPressed(View view) { // Edit button

        Intent intent =new Intent(getApplicationContext(),EditWineActivity.class);

        intent.putExtra("selectedWine",wineEditText);
        getApplicationContext().startActivity(intent);
    }
}
