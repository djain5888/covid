package com.Covid.covid19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Covid.covid19.R;

public class about_me extends AppCompatActivity {
        RelativeLayout linkedin,git,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        linkedin=findViewById(R.id.linkedin);
        git=findViewById(R.id.github);
        mail=findViewById(R.id.mail);
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.linkedin.com/in/dj9/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

git.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String url = "https://github.com/djain5888";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
});
mail.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String mailto = "mailto:djain5888@gmail.com" +
                "?cc=" + "" +
                "&subject=" + Uri.encode( "")+
                "&body=" + Uri.encode(  "");
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
});
    }
}