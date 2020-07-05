package com.example.covid19.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.example.covid19.Adapter_for_summary;
import com.example.covid19.ApiService;
import com.example.covid19.R;
import com.example.covid19.summa_full;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link comparision_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
comparision_fragment extends Fragment {
    ArrayList<String> country_list = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    Adapter_for_summary a1;

    TextView text1, text2, recovered1, recovered2, active1, active2, deaths1, deaths2;
    ArrayList<Integer> cases = new ArrayList<>();
    ArrayList<String> deaths = new ArrayList<>();
    ArrayList<String> active = new ArrayList<>();
    ArrayList<String> recovered = new ArrayList<>();
    Boolean value = false;
    Spinner spinner, spinner2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public comparision_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment comparision_fragment.
     */
    // TODO: Rename and change types and number of parameters
   public static comparision_fragment newInstance(int index) {
       comparision_fragment fragment = new comparision_fragment();
       Bundle bundle = new Bundle();
       bundle.putInt(ARG_SECTION_NUMBER, index);
       fragment.setArguments(bundle);
       return fragment;

//    String param1, String param2) {
//        comparision_fragment fragment = new comparision_fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        call4();

//        explore1=findViewById(R.id.recycler_right);
//        explore2=findViewById(R.id.recycler_left);
          View root = inflater.inflate(R.layout.fragment_comparision_fragment, container, false);



        final List<String> categories = call3();
        recovered1 =root.findViewById(R.id.Recovered1);
        recovered2 = root.findViewById(R.id.Recovered2);
        active1 = root.findViewById(R.id.Active1);
        active2 = root.findViewById(R.id.Active2);
        deaths1 = root.findViewById(R.id.Deaths1);
        deaths2 = root.findViewById(R.id.Deaths2);
        text1 = root.findViewById(R.id.text1);
        text2 = root.findViewById(R.id.text2);
        spinner2 = (Spinner)root.findViewById(R.id.appCompatSpiner2);

        final List<Integer> Confirmed = cases;

        dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
        // call("India","South-Africa");
        // call2();
        List<String> categories1 = call3();

        categories.addAll(categories1);
        categories.add("Countries list");

        //categories.add("Item 1");



        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();

        spinner2.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();

        spinner = (Spinner) root.findViewById(R.id.appCompatSpiner);
        //spinner.setAdapter(dataAdapter);


          spinner.setAdapter(dataAdapter);

        // Inflate the layout for this fragment
        return root;

    }
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

}