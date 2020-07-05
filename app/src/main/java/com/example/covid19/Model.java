package com.example.covid19;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Lon")
    @Expose
    private String lon;
    @SerializedName("Confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("Deaths")
    @Expose
    private Integer deaths;
    @SerializedName("Recovered")
    @Expose
    private Integer recovered;
    @SerializedName("Active")
    @Expose
    private Integer active;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("LocationID")
    @Expose
    private String locationID;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

}