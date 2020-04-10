package com.alex.winemanager.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Country;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddWineActivity extends AppCompatActivity {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private EditText name;
    private EditText grapes;
    private TextView country;
    private EditText region;
    private EditText description;
    private EditText year;
    private int TAKE_PHOTO_REQ_CODE=1000;
    private TextView imageName;
    private Wine wine;
    private Button addPictureButton;
    private Button addButton;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);
        setTitle("Add Wine");
        name = findViewById(R.id.addWineName);
        grapes = findViewById(R.id.addWineGrapes);
        country = findViewById(R.id.addWineCountry);
        region = findViewById(R.id.addWineRegion);
        description = findViewById(R.id.addWineDescription);
        year = findViewById(R.id.addWineYear);
        imageName = findViewById(R.id.addWineImageName);
        addButton = findViewById(R.id.wineAddButton);
        addPictureButton = findViewById(R.id.wineAddImage);
        Intent intent = getIntent();
        country.setText(intent.getStringExtra("hereYouAre"));
        addButton.setEnabled(false);
        addPictureButton.setEnabled(false);
        //Text Watcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Before user enters the text
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //On user changes the text
            }
            @Override
            public void afterTextChanged(Editable s) {
                //After user is done entering the text
                if(s.toString().trim().length()==0){
                    addButton.setEnabled(false);
                    addPictureButton.setEnabled(false);
                } else {
                    addButton.setEnabled(true);
                    addPictureButton.setEnabled(true);
                }
            }
        };

        //Add textWatcher to notify the user
        name.addTextChangedListener(textWatcher);
        grapes.addTextChangedListener(textWatcher);
        country.addTextChangedListener(textWatcher);
        region.addTextChangedListener(textWatcher);
        description.addTextChangedListener(textWatcher);
        year.addTextChangedListener(textWatcher);

    }

    @SuppressLint("ResourceType")
    public void onClickHandlerCreateWine(View view) { // Save button onClick handler
        String filePath=saveImageToStorage(bitmap);

        String wineName = name.getText().toString();
        String wineGrapes = grapes.getText().toString();
        String wineRegion = region.getText().toString();
        String wineCountry = country.getText().toString();
        int wineYear = Integer.parseInt(year.getText().toString());
        String wineDescription = description.getText().toString();
        Country wineCountryObject = wineAdmin.getCountryByName(wineCountry);
        if (wineCountryObject == null ){
            wineCountryObject = new Country(wineCountry);
            wineAdmin.addCountry(wineCountryObject);
        }
        // create Wine object
        wine = new Wine(wineName.toUpperCase(), wineYear, wineGrapes,wineCountryObject.getCountryName(), wineRegion, wineDescription, filePath);
        Log.i("imageDisplay", wine.getPicture());
        imageName.setText(wine.getPicture());


        if(checkFields()){

            wineAdmin.addWineObject(wine);
            Toast.makeText(this, getString(R.string.wineCreated), Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(this, "Text", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkFields() { // Check mandatory fields

        String wineName = name.getText().toString();
        String wineGrapes = grapes.getText().toString();
        String wineCountry = country.getText().toString();
        String wineYear = year.getText().toString();

        return !wineName.isEmpty() || !wineCountry.isEmpty() || !wineGrapes.isEmpty()  || !wineYear.isEmpty();
    }

    public void addImage(View view) { // Add picture

        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,TAKE_PHOTO_REQ_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // Camera
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==TAKE_PHOTO_REQ_CODE && resultCode== Activity.RESULT_OK){
            if (data!=null){
                bitmap=(Bitmap) data.getExtras().get("data");
            }
        }
    }
    public String saveImageToStorage(Bitmap bitmap){ // Save picture to data/com.alex.winemanager

        File file1=getApplicationContext().getFilesDir();
        File file=new File(file1,name.getText().toString()+"data.jpg");
        FileOutputStream fos=null;
        try{
            fos=new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return file.getAbsolutePath();
    }

}
