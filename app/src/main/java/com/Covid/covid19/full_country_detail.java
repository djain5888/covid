package com.Covid.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Covid.covid19.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class full_country_detail extends AppCompatActivity {
    RecyclerView explore;
    ProgressBar progressBar;
    ArrayAdapter<String> dataAdapter;
    Spinner spin;
    MyListAdapter ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_country_detail);
        Intent i1=getIntent();
        progressBar=findViewById(R.id.progress);
        String s=i1.getStringExtra("country");
        spin=findViewById(R.id.spinner2);
        TextView text=findViewById(R.id.country_name);
        text.setText(s);
        explore=findViewById(R.id.country);
        call(s);
    }
    public ArrayList<Model> call(String country)
    {
        explore.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList <Model> m1=new ArrayList<>();




        List<String> categories = new ArrayList<>();
        categories.add("Sort By :");
        categories.add("Ascending");
        //categories.add("Total Cases");
        categories.add("Descending");
        //categories.add("Total Recovered cases");
//        categories.add("Active Cases");
//        categories.add("Deaths");
        // call("India","South-Africa");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // call2();


        //categories.add("Item 1");



        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
      //  s2.get(0).value=1;

        spin.setAdapter(dataAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/total/dayone/country/")//.baseUrl("https://api.covid19api.com/live/country/"+country+"/status/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);// make a request by calling the corresponding method
        Single<Model[]> person = apiService.getPersonData(country);
//        person.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( );

        person.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Model[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Model model[]) {

                for (int i = 0; i < model.length; i++) {
//                        m1.add(model[i]);
                    m1.add(model[i]);
                    Log.i("getto", model[i].getDate());
                     ai=new MyListAdapter(m1);
                    explore.setAdapter(ai);
                    progressBar.setVisibility(View.GONE);
                }
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.i("valueofi",Integer.toString(i));
                        for(int k=0;k<m1.size();k++)
                        {
                            m1.get(k).value=i;
                        }
                        //
                        //  s2.get(0).value=1;
                        Collections.sort(m1);
                        ai.notifyDataSetChanged();



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


            }

            @Override
            public void onError(Throwable e) {
                Log.i("getto", e.toString());


            }
        });
        return m1;

    }
}