package com.example.sushantpaygude.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.Activities.MapActivity;
import com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse.Event;
import com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse.Location;
import com.example.sushantpaygude.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pankaj on 4/28/18.
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>{

    private ArrayList<Event> ticketMasterEventArrayList;
    private Context mcontext;

    public EventRecyclerViewAdapter(ArrayList<Event> ticketMasterEventArrayList, Context context) {
        this.ticketMasterEventArrayList = ticketMasterEventArrayList;
        this.mcontext = context;
    }

    @Override
    public EventRecyclerViewAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_events, parent, false);
        return new EventRecyclerViewAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewAdapter.EventViewHolder holder, int position) {
        //Log.e("Called","Bind");
        final Event event = ticketMasterEventArrayList.get(position);

        holder.textEventTitle.setText(event.getName());
        holder.textEventAddress.setText(event.getEmbedded().getVenues().get(0).getAddress().getLine1());
        Picasso.get()
                .load(event.getImages().get(0).getUrl())
                .into(holder.imageEvent);
        holder.textEventDate.setText("Date: "+event.getDates().getStart().getLocalDate());
        holder.textEventTime.setText("Time: "+event.getDates().getStart().getLocalTime());

        holder.textEventRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, MapActivity.class);
                Location location = event.getEmbedded().getVenues().get(0).getLocation();
                intent.putExtra("EventLatitude", location.getLatitude());
                intent.putExtra("EventLongitude", location.getLongitude());
                //TO DO: Use Location Service to get Current Location of User
                intent.putExtra("UserLatitude", String.valueOf(39.260700));
                intent.putExtra("UserLongitude", String.valueOf(-76.699453));
                intent.putExtra("Name", event.getName());
                mcontext.startActivity(intent);
            }
        });
    }





    @Override
    public int getItemCount() {
        return ticketMasterEventArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageEvent;
        private TextView textEventTitle;
        private TextView textEventAddress;
        private TextView textEventDate;
        private TextView textEventTime;
        private TextView textEventRoute;

        public EventViewHolder(View itemView) {
            super(itemView);
            imageEvent = itemView.findViewById(R.id.imageEvent);
            textEventTitle = itemView.findViewById(R.id.textEventTitle);
            textEventAddress = itemView.findViewById(R.id.textEventAddress);
            textEventDate = itemView.findViewById(R.id.textEventDate);
            textEventTime = itemView.findViewById(R.id.textEventTime);
            textEventRoute = itemView.findViewById(R.id.textEventRoute);
        }
    }
}
