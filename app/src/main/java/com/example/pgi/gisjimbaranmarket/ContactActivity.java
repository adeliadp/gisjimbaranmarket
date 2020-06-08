package com.example.pgi.gisjimbaranmarket;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void openMailContact(View view) {
        try{
            Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "jimbaranmarket@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contact us");
            intent.putExtra(Intent.EXTRA_TEXT, "Message Here...");
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            //TODO smth
        }
    }
}
