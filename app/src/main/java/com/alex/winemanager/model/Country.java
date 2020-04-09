package com.alex.winemanager.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryName;
    private List<Wine> wineList;


    public Country(String countryName) {
        this.countryName = countryName;
        this.wineList = new ArrayList<>();
    }
    public void addWine(Wine wine){
        wineList.add(wine);
    }

    public boolean isHere(Wine wine){
        for (Wine w:wineList) {
            if (w.getName().equals(wine.getName())){
                return true;
            }
        }
        return false;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
        for (Wine wine:wineList) {
            wine.setCountry(countryName);
        }
    }

    public void addWineCountry(Wine wine){
       wineList.add(wine);
    }

    public String getCountryName() {
        return countryName;
    }

    public List<Wine> getWineList() {
        return wineList;
    }

    public void setWineList(List<Wine> wineList) {
        this.wineList = wineList;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
