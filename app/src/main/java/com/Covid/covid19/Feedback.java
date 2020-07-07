package com.Covid.covid19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Covid.covid19.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Feedback extends AppCompatActivity {
    Button submit;
    EditText name,email,text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit=findViewById(R.id.button);
        name=findViewById(R.id.name);
        text=findViewById(R.id.messages);
        email=findViewById(R.id.mail);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2308747207296972/5120270712");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(name.getText().toString().isEmpty()||email.getText().toString().isEmpty()||text.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "PlEASE ENTER VALID DETAILS", Toast.LENGTH_SHORT).show();
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())

                {
                    Toast.makeText(getApplicationContext(), "invalid email ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mailto = "mailto:covid.19overview@gmail.com" +
                            "?cc=" + "" +
                            "&subject=" + Uri.encode( "Feedback for Corona Resource centre App from:"+name.getText())+
                            "&body=" + Uri.encode(  "Message : "+text.getText());
                     Intent i =new Intent(Intent.ACTION_SENDTO);
                             i.setData(Uri.parse(
                            mailto));

                //    i.setType("plain/text");
                //    i.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
//                    i.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Corona Resource centre App");
//                    i.putExtra(Intent.EXTRA_TEXT, "Name : "+name.getText()+"\nMessage : "+text.getText());
                    try {
                        startActivity(Intent.createChooser(i, "Send feedback..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
                //Toast.makeText(getActivity(), "Feedback submitted ", Toast.LENGTH_SHORT).show();

            }
        });

    }
}