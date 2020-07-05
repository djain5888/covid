package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_country_detail);
        Intent i1=getIntent();
        progressBar=findViewById(R.id.progress);
        String s=i1.getStringExtra("country");
        TextView text=findViewById(R.id.country_name);
        text.setText(s);
        explore=findViewById(R.id.country);
        call(s);
    }
    public ArrayList<Model> call(String country)
    {
        explore.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList <Model> m1=new ArrayList<>();
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
                    MyListAdapter ai=new MyListAdapter(m1);
                    explore.setAdapter(ai);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.i("getto", e.toString());


            }
        });
        return m1;

    }
}