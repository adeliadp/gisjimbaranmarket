package com.example.pgi.gisjimbaranmarket;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {

    private final int splash_time= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent showSplash = new Intent(Splashscreen.this , MainActivity.class);
                Splashscreen.this.startActivity(showSplash);
                Splashscreen.this.finish();
            }

        }, splash_time);
    }
}