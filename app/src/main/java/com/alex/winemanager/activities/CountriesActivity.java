package com.alex.winemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.adapters.CountryAdapter;
import com.alex.winemanager.model.WineAdmin;
import com.alex.winemanager.util.JSONHelper;

import java.io.IOException;

public class CountriesActivity extends AppCompatActivity {
    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Create up-right corner menu

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.manager_menu, menu); // Menu xml file

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addWine) { // ADD WINE

            startActivity(new Intent(getApplicationContext(), AddWineActivity.class));

        }else if(item.getItemId() == R.id.pieChart){ // ADD COUNTRY

            startActivity(new Intent(getApplicationContext(), PieChartActivity.class));

        }else if(item.getItemId() == R.id.addCountry){ // ADD COUNTRY

            startActivity(new Intent(getApplicationContext(), AddCountryActivity.class));

        } else if (item.getItemId() == R.id.rateMe) { //REDIRECT to rating

            startActivity(new Intent(getApplicationContext(), RateMeActivity.class));

        } else if (item.getItemId() == R.id.about) { //Redirect to AboutActivity

            Intent about = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(about);

        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_countries);
        listView = findViewById(R.id.wineListCountries);
        setTitle("Welcome to Wine Manager!");
        // Import from device JSON if there is no available JSON read from Drawable
        try {
            JSONHelper.importFromJSON(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        prepareDisplayList(wineAdmin); // This method makes and sets the adaptor for my first list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getApplicationContext(), WineListActivity.class);
               intent.putExtra("selectedCountry", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }

    private void prepareDisplayList(@NonNull WineAdmin wineAdmin){ // Display countries

        CountryAdapter arrayAdapter = new CountryAdapter(this, wineAdmin.getCountryList());

        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onPause() { // Export JSON
        JSONHelper.exportToJSON(this, wineAdmin.getAllWines());
        super.onPause();

    }

    @Override
    protected void onResume() { // Notify changes on list
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onBackPressed() { //Disable back button
    }
}