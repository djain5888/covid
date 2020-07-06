package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
                    String mailto = "mailto:djain5888@gmail.com" +
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