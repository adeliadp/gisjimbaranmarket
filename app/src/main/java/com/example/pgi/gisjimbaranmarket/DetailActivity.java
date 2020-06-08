package com.example.pgi.gisjimbaranmarket;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textname, textaddress, texthours, textphone;
    ImageView dataimges;
    Button btncall;
    public static String id,name, address, image, hours, phone;
    public static Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textname=findViewById(R.id.name_market);
        textaddress=findViewById(R.id.address_market);
        texthours=findViewById(R.id.hours_market);
        textphone=findViewById(R.id.phone_market);
        btncall=findViewById(R.id.btn_call);
        dataimges=findViewById(R.id.img_data);

        textname.setText(name);
        textaddress.setText(address);
        texthours.setText(hours);
        textphone.setText(phone);
        Picasso.get().load(image).into(dataimges);
        btncall.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phone));
        startActivity(call);
    }
}


