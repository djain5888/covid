package com.example.covid19;


import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Switch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class get_summary implements Comparable<get_summary> {

    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("Slug")
    @Expose
    private String slug;
    @SerializedName("NewConfirmed")
    @Expose
    private Integer newConfirmed;
    @SerializedName("TotalConfirmed")
    @Expose
    private Integer totalConfirmed;
    @SerializedName("NewDeaths")
    @Expose
    private Integer newDeaths;
    @SerializedName("TotalDeaths")
    @Expose
    private Integer totalDeaths;
    @SerializedName("NewRecovered")
    @Expose
    private Integer newRecovered;
    @SerializedName("TotalRecovered")
    @Expose
    private Integer totalRecovered;
    @SerializedName("Date")
    @Expose
    private String date;
    public int value=0;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(Integer newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(Integer totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Integer newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(Integer newRecovered) {
        this.newRecovered = newRecovered;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(Integer totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(get_summary o) {
        Log.i("heloso",Integer.toString(value));


            switch(value)
            { case 1:
                {

                    return o.getNewConfirmed()-this.getNewConfirmed();
                }

             case 2:
                 {
                 return o.getTotalConfirmed()-this.getTotalConfirmed();
                 }
                case 3:
                {
                    return o.getNewRecovered()-this.getNewRecovered();
                }
                case 4:
                {
                    return o.getTotalRecovered()-this.getTotalRecovered();
                }
                case 5:
                {
                    int active1=o.getTotalConfirmed()-o.getTotalRecovered()-o.getTotalDeaths();
                    int active2=this.getTotalConfirmed()-this.getTotalRecovered()-this.getTotalDeaths();
                    return active1-active2;
                }
                case 6:
                {
                    return o.getTotalDeaths()-this.getTotalDeaths();
                }
                default:
                {
                    return 0;
                }




        }
        //SharedPreferences prefs =getApplicationContext().getSharedPreferences("dj", MODE_PRIVATE);
//        String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//        int idName = prefs.getInt("idName", 0);

    }
}






