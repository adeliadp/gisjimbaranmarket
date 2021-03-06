package com.example.pgi.gisjimbaranmarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Camera;
import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] name, address, hours, image, id, phone;
    int sumData;
    private Double[] latitude, longitude;
    Boolean MarkerD[];
    LatLng latLng[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getLocation(){
        String url = "https://jimbaranmarket.000webhostapp.com/location.php";
        JsonArrayRequest request = new JsonArrayRequest
                (Request.Method.GET, url, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        sumData = response.length();
                        Log.d("DEBUG_", "PARSE JSON");
                        latLng = new LatLng[sumData];
                        MarkerD = new Boolean[sumData];
                        name = new String[sumData];
                        address = new String[sumData];
                        hours = new String[sumData];
                        phone = new String[sumData];
                        image = new String[sumData];
                        id = new String[sumData];
                        latitude = new Double[sumData];
                        longitude = new Double[sumData];

                        for (int i = 0; i < sumData; i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                id[i] = data.getString("id");
                                latLng[i] = new LatLng(data.getDouble("latitude"), data.getDouble("longitude"));
                                name[i] = data.getString("name");
                                address[i] = data.getString("address");
                                hours[i] = data.getString("hours");
                                phone[i] = data.getString("phone");
                                image[i] = data.getString("image");
                                latitude[i] = data.getDouble("latitude");
                                longitude[i] = data.getDouble("longitude");
                                MarkerD[i] = false;

                                Marker marketnew = mMap.addMarker(new MarkerOptions()
                                        .position(latLng[i])
                                        .snippet(address[i])
                                        .title(name[i])
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.newmarker)));
                                
                                marketnew.showInfoWindow();

                            }


                            catch (JSONException je) {

                            }
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng[i], 13f));


                        }

                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {

                                for (int i = 0; i < sumData; i++) {
                                    if (marker.getTitle().equals(name[i])) {
                                        if(MarkerD[i]){
                                            DetailActivity.id = id[i];
                                            DetailActivity.name = name[i];
                                            DetailActivity.address = address[i];
                                            DetailActivity.image = image[i];
                                            DetailActivity.hours = hours[i];
                                            DetailActivity.phone = phone[i];
                                            DetailActivity.latitude = latitude[i];
                                            DetailActivity.longitude = longitude[i];
                                            Intent movetoDetail = new Intent(MapsActivity.this, DetailActivity.class);
                                            startActivity(movetoDetail);
                                            MarkerD[i] = false;
                                        } else {
                                            MarkerD[i] = true;
                                            marker.showInfoWindow();
                                            Toast pesan = Toast.makeText(MapsActivity.this, "Click one again to see the detail", Toast.LENGTH_LONG);
                                            TextView tv = pesan.getView().findViewById(R.id.message);
                                            if (tv != null)
                                                tv.setGravity(Gravity.CENTER);
                                            pesan.show();

                                        }
                                    }else {
                                        MarkerD[i] = false;
                                    }
                                }

                                return false;
                            }
                        });

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this );
                        builder.setTitle("Error");
                        builder.setMessage("Connection Failed");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.setPositiveButton("Reload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getLocation();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
        Volley.newRequestQueue(this ).add(request);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocation();
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
