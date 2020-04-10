package com.alex.winemanager.model;

import android.util.Log;
import android.widget.RatingBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WineAdmin {

    private List<Rating> ratings = genRating();
    private List<Country> countries =new ArrayList<>(); // main storage
    private static WineAdmin instance = null;

    public static WineAdmin getInstance() { //Singleton
        if (instance == null) {
            instance = new WineAdmin();
        }
        return instance;
    }
    // Set countries
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }


    public boolean removeWineByName(String wineName) {// Remove wine by name
        Wine wine = getWineByName(wineName);
        for (Country country:countries) {
            if (country.isHere(wine)){
                getWinesOfACountry(country.getCountryName()).remove(wine);
                return true;
            }
        }
        return false;
    }

    public void removeWine(Wine wine){ // Remove wine
        for (Country country:countries) {
            for (Wine wineCountry:country.getWineList()) {
                if (wineCountry.getName().equals(wine.getName())){
                    country.getWineList().remove(wine);
                    break;
                }
            }
        }
    }
    public Country getCountryByName(String countryName){ // Get country by name
        for (Country country:countries) {
            if (country.getCountryName().equals(countryName)) return country;
        }
        return null;
    }
    public void addCountry(Country country){ // Add country
        if(countries.isEmpty()){
            countries.add(country);
        }else{
            if (!countries.contains(country)){
                countries.add(country);
            }
        }
    }

    public void addWineObject(Wine wine) { // Add wine object
        boolean doesExists = false;

        for (Country country:countries) {
            if (country.getCountryName().equals(wine.getCountry())){
                country.addWine(wine);
                doesExists = true;
            }
        }
        if(!doesExists){
            Country country = new Country(wine.getCountry());
            country.addWine(wine);
            countries.add(country);
        }
    }

    public void addWine(JSONObject jsonObject) throws JSONException {
        boolean doesExist = false;
        for (Country country:countries) {
            if (country.getCountryName().equals(jsonObject.getString("country"))){
                Wine wine = new Wine(jsonObject);
                country.addWine(wine);
                doesExist = true;
                break;
            }
        }
        if (!doesExist){
            Country country = new Country(jsonObject.getString("country"));
            countries.add(country);
            Wine wine = new Wine(jsonObject);
            country.addWine(wine);
        }
    }
    public List<Wine> getAllWines(){ // Get wine list
        List<Wine> wines = new ArrayList<>();
        for (Country country:countries) {
            wines.addAll(country.getWineList());
        }
        return wines;
    }

    public List<Country> getCountryList() { // Get country list
        return countries;
    }

    public void setWineAndCountries(List<Country> wineAndCountries) {
        this.countries = wineAndCountries;
    }

    public List<Wine> getWinesOfACountry(String countryName) { // Get wines of a country
        for (Country country:countries) {
            if (country.getCountryName().equals(countryName)){
                return country.getWineList();
            }
        }
        return null;
    }

    public Wine getWineByName(String wineName) { // Get Wine by Name
        Log.i("wine", wineName);
        for (Country country:countries) {
            for (Wine wine:country.getWineList()) {
                if (wine.getName().equals(wineName)){
                    return wine;
                }
            }
        }
        return null;
    }

    public static void setInstance(WineAdmin instance) {
        WineAdmin.instance = instance;
    }

    //Rating calculation
    private ArrayList<Rating> genRating() {
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(5));
        ratings.add(new Rating(5));
        return ratings;
    }

    public float getAverageRating() {
        float average = 0;
        for (Rating rating : ratings) {
            average += rating.getRating();
        }
        return roundToHalf(average/getNumberOfRatings());
    }

    public void newRating(RatingBar ratingBar) {
        Rating rating = new Rating(ratingBar);
        addRating(rating);
    }

    private void addRating(Rating rating) {
        ratings.add(rating);
    }

    public int getNumberOfRatings() {
        return ratings.size();
    }

    private float roundToHalf(float x) {
        return (Math.round(x * 2) / 2.0f);
    }


    @Override
    public String toString() {
        return countries.toString();
    }


}
