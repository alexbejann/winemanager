package com.alex.winemanager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alex.winemanager.R;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

import java.util.List;

public class WineListAdapter extends ArrayAdapter<Wine> {

    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private LayoutInflater layoutInflater;
    private List<Wine> wineList;
    private TextView wineName;
    private ImageButton deleteButton;


    public WineListAdapter(@NonNull Context context, @NonNull List<Wine> objects) {
        super(context, R.layout.wine_list, objects);
        layoutInflater = LayoutInflater.from(context);
        wineList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.wine_list, parent, false);
        }

        if (convertView != null){
            wineName = convertView.findViewById(R.id.wineName);
            wineName.setText(wineList.get(position).getName());
            deleteButton = convertView.findViewById(R.id.deleteImageButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("removeCountry", wineList.get(position).getName());
                    wineAdmin.removeWine(wineList.get(position));
                    //wineList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }
}
