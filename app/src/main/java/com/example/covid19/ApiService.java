package com.example.covid19;

import com.example.covid19.Models.Xml_news.RSSFeed;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    @GET
Single<Model[]> getPersonData(@Url String url);
    @GET("summary")
Single<summa_full> getSummary();
    @GET("countries")
    Single<ArrayList<CountryName>> getcountries();
    @GET("news-english.xml")
    Single<RSSFeed> getNews();
}