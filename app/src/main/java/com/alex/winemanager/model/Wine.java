package com.alex.winemanager.model;


import org.json.JSONException;
import org.json.JSONObject;

public class Wine {


    private String name;
    private int year;
    private String grapes;
    private  String country;
    private String region;
    private String description;
    private String picture = "default";

    public Wine(String name, int year, String grapes, String country, String region, String description, String picture) {
        this.name = name;
        this.year = year;
        this.grapes = grapes;
        this.country = country;
        this.region = region;
        this.description = description;
        this.picture = picture;
    }

    public Wine(JSONObject jsonObject) {
        try {
            this.name = jsonObject.getString("name");
            this.year = jsonObject.getInt("year");
            this.grapes = jsonObject.getString("grapes");
            this.country = jsonObject.getString("country");
            this.region = jsonObject.getString("region");
            this.description = jsonObject.getString("description");
            this.picture = jsonObject.getString("picture");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return name+" "+year+" "+grapes+" "+country+" "+region+" "+description+" "+picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGrapes() {
        return grapes;
    }

    public void setGrapes(String grapes) {
        this.grapes = grapes;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
