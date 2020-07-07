package com.Covid.covid19;

import com.Covid.covid19.Models.Xml_news.RSSFeed;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.GET;
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