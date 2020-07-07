package com.Covid.covid19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.Covid.covid19.R;
import com.Covid.covid19.ui.main.SectionsPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AppBarConfiguration appBarConfiguration;

    OkHttpClient client = new OkHttpClient();
    RecyclerView r1;
    SearchView s1;
    String strAppLink ;

    List<get_summary> s2;
    ProgressBar p1;


    Adapter_for_summary a1;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer;
        final String appPackageName = getApplicationContext().getPackageName();
        strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
              R.string.navigation_drawer_close);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View view =navigationView.inflateHeaderView(R.layout.nav_header_main);
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });




//        Toast.makeText(getBaseContext(),
//                "Swipe right - finish()",
//
//                Toast.LENGTH_SHORT).show();

        r1 = findViewById(R.id.recycler_view);
        //p1=findViewById(R.id.progress);
//        p1.setIndeterminate(true);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch(item.getItemId()) {
            case R.id.nav_share: {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, strAppLink);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            }
            case R.id.nav_feedback:
                {
                Intent i1=new Intent(this, Feedback.class);
                startActivity(i1);
                break;
            }
            case R.id.nav_about_us:
            {
                Intent i1=new Intent(this, about_me.class);
                startActivity(i1);
                break;

            }
        }

        return false;
    }


}


