package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class comparision extends AppCompatActivity {
    RecyclerView explore1, explore2;
    ArrayList<String> country_list = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    TextView text1, text2, recovered1, recovered2, active1, active2, deaths1, deaths2;
    ArrayList<Integer> cases = new ArrayList<>();
    ArrayList<String> deaths = new ArrayList<>();
    ArrayList<String> active = new ArrayList<>();
    ArrayList<String> recovered = new ArrayList<>();
    Boolean value = false;
    Spinner spinner, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_comparision);
        call4();

//        explore1=findViewById(R.id.recycler_right);
//        explore2=findViewById(R.id.recycler_left);

        final List<String> categories = call3();
        recovered1 = findViewById(R.id.Recovered1);
        recovered2 = findViewById(R.id.Recovered2);
        active1 = findViewById(R.id.Active1);
        active2 = findViewById(R.id.Active2);
        deaths1 = findViewById(R.id.Deaths1);
        deaths2 = findViewById(R.id.Deaths2);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        final List<Integer> Confirmed = cases;

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // call("India","South-Africa");
        // call2();
        List<String> categories1 = call3();

        categories.addAll(categories1);
        categories.add("Countries list");

        //categories.add("Item 1");


        spinner2 = findViewById(R.id.appCompatSpinner2);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();

        spinner2.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

        spinner = (Spinner) findViewById(R.id.appCompatSpinner);
        spinner.setAdapter(dataAdapter);


        //   spinner.setAdapter(dataAdapter);

    }

    //    public ArrayList<Model> call(String country1,String country2)
//    {
////        explore1.setLayoutManager(new LinearLayoutManager(this));
//        final ArrayList <Model> m1=new ArrayList<>();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.covid19api.com/total/dayone/country/")//.baseUrl("https://api.covid19api.com/live/country/"+country+"/status/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);// make a request by calling the corresponding method
//        Single<Model[]> person = apiService.getPersonData(country1);
////        person.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe( );
//
//        person.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<Model[]>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Model model[]) {
//
//                        for (int i = 0; i < model.length; i++) {
////                        m1.add(model[i]);
//                            m1.add(model[i]);
//                            Log.i("getto", model[i].getDate());
//                            MyListAdapter ai=new MyListAdapter(m1);
//                            explore1.setAdapter(ai);
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("getto", e.toString());
//
//
//                    }
//                });
//        explore2.setLayoutManager(new LinearLayoutManager(this));
//        final ArrayList <Model> m2=new ArrayList<>();
////        Retrofit retrofit2 = new Retrofit.Builder()
////                .baseUrl("https://api.covid19api.com/total/dayone/country/")//.baseUrl("https://api.covid19api.com/live/country/"+country+"/status/")
////                .addConverterFactory(GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                .build();
//        ApiService apiService2 = retrofit.create(ApiService.class);// make a request by calling the corresponding method
//        Single<Model[]> person2 = apiService2.getPersonData(country2);
////        person.subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe( );
//
//        person2.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<Model[]>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Model model[]) {
//
//                        for (int i = 0; i < model.length; i++) {
////                        m1.add(model[i]);
//                            m2.add(model[i]);
//                            Log.i("getto", model[i].getDate());
//                            MyListAdapter ai=new MyListAdapter(m2);
//                            explore2.setAdapter(ai);
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("getto", e.toString());
//
//
//                    }
//                });
//
//        return m1;
//
//    }
//
    public ArrayList<String> call3() {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService2 = retrofit2.create(ApiService.class);
        Single<summa_full> person2 = apiService2.getSummary();
        person2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<summa_full>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(summa_full summa_full) {
                        Log.i("maje", summa_full.getDate());

                        for (int i = 0; i < summa_full.getCountries().size(); i++) {
                            country_list.add(summa_full.getCountries().get(i).getCountry());
                            //Confirmed.add(summa_full.getCountries().get(i).getTotalConfirmed());

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("maje", e.toString());


                    }
                });
        //  dataAdapter.notifyDataSetChanged();

        return country_list;
    }

    public void call4() {
        final boolean[] a = {false};
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService2 = retrofit2.create(ApiService.class);
        Single<summa_full> person2 = apiService2.getSummary();
        person2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<summa_full>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(summa_full summa_full) {
                        Log.i("majecall2", summa_full.getDate());

                        for (int i = 1; i < summa_full.getCountries().size() + 1; i++) {
                            //country_list.add(summa_full.getCountries().get(i).getCountry());
                            cases.add(summa_full.getCountries().get(i - 1).getTotalConfirmed());
                            deaths.add(Integer.toString(summa_full.getCountries().get(i - 1).getTotalDeaths()));
                            recovered.add(Integer.toString(summa_full.getCountries().get(i - 1).getTotalRecovered()));
                            active.add(Integer.toString(summa_full.getCountries().get(i - 1).getTotalConfirmed() - summa_full.getCountries().get(i - 1).getTotalRecovered() - summa_full.getCountries().get(i - 1).getTotalDeaths()));
                            a[0] = true;
                            value = true;

                            Log.i("inon", "de");
                            if (value == true) {
                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        text1.setText(cases.get(i - 1).toString());
                                        deaths1.setText(deaths.get(i - 1));
                                        recovered1.setText(recovered.get(i - 1));
                                        active1.setText(active.get(i - 1));

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }

                                });
                                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        text2.setText(cases.get(i - 1).toString());
                                        deaths2.setText(deaths.get(i - 1));
                                        recovered2.setText(recovered.get(i - 1));
                                        active2.setText(active.get(i - 1));

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }


                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("maje", e.toString());


                    }
                });
        //  dataAdapter.notifyDataSetChanged();

        Log.i("heelo", Boolean.toString(a[0]));


        //  return  cases;

    }


    //  public void call2()
//    {
//
//
//        Retrofit retrofit2 = new Retrofit.Builder()
//                .baseUrl("https://api.covid19api.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        ApiService apiService2 = retrofit2.create(ApiService.class);// make a request by calling the corresponding method
//        Single<ArrayList<CountryName>> person2 = apiService2.getcountries();
//        person2.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<ArrayList<CountryName>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(ArrayList<CountryName> summa_full) {
//                        for(int i=0;i<summa_full.size();i++){
//                        Log.i("maje",summa_full.get(i).getCountry());}
//
////                        s2 =summa_full.getCountries();
////                        a1=new Adapter_for_summary(s2);
////                        r1.setAdapter(a1);
////                        Intent i1=new Intent(MainActivity.this,comparision.class);
////                        startActivity(i1);
////                a1.getFilter().filter("India");
//
////                s1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////                    @Override
////                    public boolean onQueryTextSubmit(String s) {
////                        a1.getFilter().filter(s);
////                        return false;
////                    }
////
////                    @Override
////                    public boolean onQueryTextChange(String s) {
////                        return false;
////                    }
////                });
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("maje",e.toString());
//
//
//
//                    }
//                });
    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new DecelerateInterpolator());
//          getWindow().setExitTransition(slide);
//          getWindow().setEnterTransition(slide);
        }
//
//    }
    }
}