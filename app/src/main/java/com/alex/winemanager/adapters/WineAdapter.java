package com.alex.winemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

import java.util.List;

public class WineAdapter extends ArrayAdapter<Wine> {

    private LayoutInflater layoutInflater;

    private ImageView imageView;
    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private TextView name;
    private TextView year;
    private TextView grapes;
    private TextView country;
    private TextView region;
    private TextView description;

    public WineAdapter(@NonNull Context context, @NonNull List<Wine> objects) {
        super(context, R.layout.wine_profile, objects);
        layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.wine_profile, parent, false);
        }
        if (convertView != null) {
            imageView = convertView.findViewById(R.id.wineImage);

            name = convertView.findViewById(R.id.name);
            year = convertView.findViewById(R.id.year);
            grapes = convertView.findViewById(R.id.grapes);
            country = convertView.findViewById(R.id.country);
            region = convertView.findViewById(R.id.region);
            description = convertView.findViewById(R.id.description);
        }
        return super.getView(position, convertView, parent);
    }
}
