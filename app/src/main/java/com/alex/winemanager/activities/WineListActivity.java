package com.alex.winemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.alex.winemanager.R;
import com.alex.winemanager.adapters.WineListAdapter;
import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;

import java.util.List;
import java.util.Objects;

public class WineListActivity extends AppCompatActivity {
    private WineAdmin wineAdmin = WineAdmin.getInstance();
    private ListView listView;
    private int hereYouAre;
    private List<Wine> wineList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.manager_menu_wine, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addWine) { // ADD WINE FORM
            Intent intent = new Intent(getApplicationContext(), AddWineActivity.class);
            intent.putExtra("hereYouAre",  Objects.requireNonNull(getIntent().getExtras()).getString("selectedCountry"));
            startActivity(intent);

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
        setContentView(R.layout.activity_wine_list);
        setTitle("Wines");
        listView = findViewById(R.id.listView_wines);
        wineList = wineAdmin.getWinesOfACountry(Objects.requireNonNull(getIntent().getExtras()).getString("selectedCountry"));
        WineListAdapter arrayAdapter = new WineListAdapter(this, wineList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), WineProfile.class);
                hereYouAre = position;
                intent.putExtra("selectedWine", wineList.get(position).getName());
                Log.i("select", listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}
