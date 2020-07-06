package com.example.covid19.ui.main;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.covid19.Adapter_for_summary;
import com.example.covid19.ApiService;
import com.example.covid19.Models.Xml_news.RSSFeed;
import com.example.covid19.R;
import com.example.covid19.get_summary;
import com.example.covid19.summa_full;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Single;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static android.content.Context.MODE_PRIVATE;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    RecyclerView r1;
    SearchView s1;
    ArrayAdapter<String> dataAdapter;
    Spinner spin;
    List<get_summary> s2;
    ProgressBar progress;


    Adapter_for_summary a1;


    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progress=root.findViewById(R.id.progress);
        spin=root.findViewById(R.id.spinner);
        r1 = root.findViewById(R.id.recycler_view);

        r1.setLayoutManager(new LinearLayoutManager(getContext()));
        callapi();


//        Retrofit retrofit2 = new Retrofit.Builder()
//                .baseUrl("https://api.covid19api.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//
//        ApiService apiService2 = retrofit2.create(ApiService.class);// make a request by calling the corresponding method
//        Single<summa_full> person2 = apiService2.getSummary();
//        person2.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<summa_full>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(summa_full summa_full) {
//                        Log.i("maje", summa_full.getDate());
//                        s2 = summa_full.getCountries();
//                        a1 = new Adapter_for_summary(s2,getContext());
//                        r1.setAdapter(a1);
//
//                        List<String> categories = new ArrayList<>();
//                        categories.add("Sort By :");
//                        categories.add("New Cases");
//                        categories.add("Total Cases");
//                        categories.add("New Recovered cases");
//                        categories.add("Total Recovered cases");
//                        categories.add("Active Cases");
//                        categories.add("Deaths");
//                        dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
//                        // call("India","South-Africa");
//                        // call2();
//
//
//                        //categories.add("Item 1");
//
//
//
//                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        dataAdapter.notifyDataSetChanged();
//                        s2.get(0).value=1;
//
//                        spin.setAdapter(dataAdapter);
//                        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//                            @Override
//                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                Log.i("valueofi",Integer.toString(i));
//                                for(int k=0;k<s2.size();k++)
//                                {
//                                    s2.get(k).value=i;
//                                }
//                              //
//                                //  s2.get(0).value=1;
//                                Collections.sort(s2);
//                                a1.notifyDataSetChanged();
//
//
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> adapterView) {
//
//                            }
//                        });
//
//                        a1.notifyDataSetChanged();
//                        progress.setVisibility(View.GONE);
//                       // Intent i1 = new Intent(MainActivity.this, comparision.class);
//                        //  startActivity(i1);
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
//                        Log.i("maje123", e.toString());
//
//
//                    }
//                });
//
//
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//            }
//        });
//
//
//
//        Retrofit retrofit3 = new Retrofit.Builder()
//                .baseUrl("https://www.who.int/rss-feeds/")
//                .client(new OkHttpClient())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//
//
//
//        ApiService apiService3 = retrofit3.create(ApiService.class);
//        // make a request by calling the corresponding method
//
//        Single<RSSFeed> n1=apiService3.getNews();
//        n1.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<RSSFeed>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("new part",e.toString());
//
//
//
//                    }
//
//                    @Override
//                    public void onSuccess(RSSFeed news) {
//                        Log.i("new part",news.getArticleList().get(2).getTitle());
//                        Log.i("desc",news.getArticleList().get(3).getDescription());
//                        String yourString=news.getArticleList().get(2).getDescription();
//                        //desc.contains("img src=");
//
//
//                        String str = "ZZZZL <%= dsn %> AFFF <%= AFG %>";
////                        String s=yourString.substring(yourString.indexOf("img src=") + 3 , yourString.length());
////                        Log.i("desc",s);
//                        Pattern pattern = Pattern.compile("<%=(.*?)%>", Pattern.DOTALL);
//                        Matcher matcher = pattern.matcher(str);
//                        while (matcher.find()) {
//                            Log.i("getlost",matcher.group(1));
//                            System.out.println(matcher.group(1));
//                        }
//
//
//
//
//                    }
//                });

        return root;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        //menu.clear();
       // MenuItem search=menu.findItem(R.id.action_settings);
       // search.getMenuInfo();


        MenuItem item = menu.findItem(R.id.action_search);



        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        androidx.appcompat.widget.SearchView searh = (androidx.appcompat.widget.SearchView) item.getActionView();

        item.setActionView(searh);

            searh.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
              Log.i("inquert",s2.get(2).getCountry());
              if(a1!=null)
              {

                    a1.getFilter().filter(query);}

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if(a1!=null)
                    a1.getFilter().filter(newText);
                    return false;
                }
            });



//        return super.onCreateOptionsMenu(menu);



    }
    public void callapi()
    {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiService apiService2 = retrofit2.create(ApiService.class);// make a request by calling the corresponding method
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
                        s2 = summa_full.getCountries();
                        a1 = new Adapter_for_summary(s2,getContext());
                        r1.setAdapter(a1);

                        List<String> categories = new ArrayList<>();
                        categories.add("Sort By :");
                        categories.add("New Cases");
                        categories.add("Total Cases");
                        categories.add("New Recovered cases");
                        categories.add("Total Recovered cases");
                        categories.add("Active Cases");
                        categories.add(" Total Deaths");
                        dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
                        // call("India","South-Africa");
                        // call2();


                        //categories.add("Item 1");



                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        s2.get(0).value=1;

                        spin.setAdapter(dataAdapter);
                        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.i("valueofi",Integer.toString(i));
                                for(int k=0;k<s2.size();k++)
                                {
                                    s2.get(k).value=i;
                                }
                                //
                                //  s2.get(0).value=1;
                                Collections.sort(s2);
                                a1.notifyDataSetChanged();



                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        a1.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);
                        // Intent i1 = new Intent(MainActivity.this, comparision.class);
                        //  startActivity(i1);
//                a1.getFilter().filter("India");

//                s1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String s) {
//                        a1.getFilter().filter(s);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String s) {
//                        return false;
//                    }
//                });


                    }

                    @Override
                    public void onError(Throwable e) {
                        AlertDialog.Builder builder
                                = new AlertDialog.Builder(getActivity());

                        builder.setMessage("Do you want to Retry loading page ?");

                        // Set Alert Title
                        builder.setTitle("Problem Loading Page");

                        // Set Cancelable false
                        // for when the user clicks on the outside
                        // the Dialog Box then it will remain show
                        builder.setCancelable(false);

                        // Set the positive button with yes name
                        // OnClickListener method is use of
                        // DialogInterface interface.

                        builder
                                .setPositiveButton(
                                        "Yes",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which)
                                            {

                                                // When the user click yes button
                                                // then app will close
                                                callapi();
                                            }
                                        });

                        // Set the Negative button with No name
                        // OnClickListener method is use
                        // of DialogInterface interface.
                        builder
                                .setNegativeButton(
                                        "No",
                                        new DialogInterface
                                                .OnClickListener() {

                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which)
                                            {

                                                // If user click no
                                                // then dialog box is canceled.
                                                dialog.cancel();
                                            }
                                        });

                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();

                        // Show the Alert Dialog box
                        alertDialog.show();
                        Log.i("maje123", e.toString());


                    }
                });


        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });




    }


}