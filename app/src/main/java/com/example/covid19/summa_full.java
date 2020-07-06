package com.example.covid19;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class summa_full  {

    @SerializedName("Global")
    @Expose
    private Global global;
    @SerializedName("Countries")
    @Expose
    private List<get_summary> countries = null;
    @SerializedName("Date")
    @Expose
    private String date;

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public List<get_summary> getCountries() {
        return countries;
    }

    public void setCountries(List<get_summary> countries) {
        this.countries = countries;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}