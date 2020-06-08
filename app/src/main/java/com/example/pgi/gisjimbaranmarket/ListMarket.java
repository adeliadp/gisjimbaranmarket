package com.example.pgi.gisjimbaranmarket;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListMarket extends AppCompatActivity {

    ArrayList<String> marketName = new ArrayList<>();
    ArrayList<String> marketAddress = new ArrayList<>();
    ArrayList<String> marketHours = new ArrayList<>();
    int sumData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_market);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        String url = "https://jimbaranmarket.000webhostapp.com/location.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sumData = response.length();
                for (int i = 0; i < sumData; i++) {
                    try {
                        JSONObject data = response.getJSONObject(i);
                        marketName.add(data.getString("name"));
                        marketAddress.add(data.getString("address"));
                        marketHours.add(data.getString("hours"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                CustomAdapter customAdapter = new CustomAdapter(ListMarket.this, marketName, marketAddress, marketHours);
                recyclerView.setAdapter(customAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this ).add(request);



    }
}
