package com.example.pgi.gisjimbaranmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://jimbaranmarket.000webhostapp.com/location.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("myTag", "This is my message");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    public void openMap(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    public void openAbout(View view) {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    public void openContact(View view) {
        Intent i = new Intent(MainActivity.this, ContactActivity.class);
        startActivity(i);
    }

    public void listMarket(View view) {
        Intent i = new Intent(MainActivity.this, ListMarket.class);
        startActivity(i);
    }
}
