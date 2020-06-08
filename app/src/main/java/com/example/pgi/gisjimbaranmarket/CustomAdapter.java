package com.example.pgi.gisjimbaranmarket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> marketName;
    ArrayList<String> marketAddress;
    ArrayList<String> marketHours;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> emailIds, ArrayList<String> mobileNumbers) {
        this.context = context;
        this.marketName = personNames;
        this.marketAddress = emailIds;
        this.marketHours = mobileNumbers;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(marketName.get(position));
        holder.address.setText("Address: " + marketAddress.get(position));
        holder.hours.setText("Open Hours: " + marketHours.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, marketName.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return marketName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, address, hours;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            hours = (TextView) itemView.findViewById(R.id.hours);

        }
    }
}
