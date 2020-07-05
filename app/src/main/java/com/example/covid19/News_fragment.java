package com.example.covid19;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid19.Models.Xml_news.RSSFeed;

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
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link News_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class News_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private String mParam2;

    public News_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment News_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static News_fragment newInstance(int index) {
        News_fragment fragment = new News_fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_news_fragment, container, false);
        final RecyclerView newsrecycler=root.findViewById(R.id.news_recycler);
        newsrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit3 = new Retrofit.Builder()
                .baseUrl("https://www.who.int/rss-feeds/")
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();




        ApiService apiService3 = retrofit3.create(ApiService.class);
        // make a request by calling the corresponding method

        Single<RSSFeed> n1=apiService3.getNews();
        n1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RSSFeed>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("new part",e.toString());


                    }

                    @Override
                    public void onSuccess(RSSFeed news) {
                        Log.i("new part",news.getArticleList().get(2).getTitle());
                        Log.i("desc",news.getArticleList().get(3).getDescription());
                        String yourString=news.getArticleList().get(2).getDescription();
                        //desc.contains("img src=");
                        News_adapter n1=new News_adapter(news);
                        newsrecycler.setAdapter(n1);

                        String str = "ZZZZL <%= dsn %> AFFF <%= AFG %>";
//                        String s=yourString.substring(yourString.indexOf("img src=") + 3 , yourString.length());
//                        Log.i("desc",s);
                        Pattern pattern = Pattern.compile("<%=(.*?)%>", Pattern.DOTALL);
                        Matcher matcher = pattern.matcher(str);
                        while (matcher.find()) {
                            Log.i("getlost",matcher.group(1));
                            System.out.println(matcher.group(1));
                        }




                    }
                });

        return root;
    }


    }
