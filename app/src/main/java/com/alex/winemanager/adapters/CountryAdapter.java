package com.alex.winemanager.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alex.winemanager.R;
import com.alex.winemanager.activities.EditCountryActivity;
import com.alex.winemanager.model.Country;
import com.alex.winemanager.model.WineAdmin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private LayoutInflater layoutInflater;
    private TextView textView;
    private ImageView imageView;
    private List<Country> countryList;
    private ImageButton deleteButton;
    private ImageButton editButton;

    public CountryAdapter(@NonNull Context context, @NonNull List<Country> objects) {
        super(context, R.layout.countrylist, objects);
        layoutInflater = LayoutInflater.from(context);
        countryList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.countrylist, parent, false);
        }

        if (convertView != null){
            imageView = convertView.findViewById(R.id.flag);

            InputStream imageStream = null;
            try {
                // get input stream
                imageStream  = getContext().getAssets().open(countryList.get(position).getCountryName().toLowerCase().replaceAll(" ","-")+".png");
                // load image as Drawable
                Drawable drawable= Drawable.createFromStream(imageStream, null);
                // set image to ImageView
                imageView.setImageDrawable(drawable);
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            finally {
                if (imageStream != null){
                    try {
                        imageStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            textView = convertView.findViewById(R.id.countryName);
            textView.setText(countryList.get(position).getCountryName());
            deleteButton = convertView.findViewById(R.id.deleteImageButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { // Delete country button
                    Log.i("removeCountry", countryList.get(position).getCountryName());
                    wineAdmin.getCountryList().remove(countryList.get(position)); // Remove country from list
                    notifyDataSetChanged();
                }
            });
            editButton = convertView.findViewById(R.id.editCountryList);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { // Edit country button
                    Log.i("editCountry", countryList.get(position).getCountryName());
                    Intent intent = new Intent(getContext(), EditCountryActivity.class);
                    intent.putExtra("country",countryList.get(position).getCountryName());
                    getContext().startActivity(intent);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

}
