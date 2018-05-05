package com.example.sushantpaygude.finalproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse.Event;
import com.example.sushantpaygude.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pankaj on 4/28/18.
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>{

    private ArrayList<Event> ticketMasterEventArrayList = new ArrayList<>();


    public EventRecyclerViewAdapter(ArrayList<Event> ticketMasterEventArrayList) {
        this.ticketMasterEventArrayList = ticketMasterEventArrayList;
    }

    @Override
    public EventRecyclerViewAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_events, parent, false);
        return new EventRecyclerViewAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewAdapter.EventViewHolder holder, int position) {
        //Log.e("Called","Bind");
        Event event = ticketMasterEventArrayList.get(position);

        holder.textEventTitle.setText(event.getName());
        //holder.textEventAddress.setText(event.getEmbedded().getVenues().get(0).getAddress().toString());
//        Picasso.get()
//                .load(event.getImages().get(0).)
//                .into(holder.imageEvent);
        holder.textEventUrl.setText(String.valueOf(event.getUrl()));
    }

    @Override
    public int getItemCount() {
        return ticketMasterEventArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageEvent;
        private TextView textEventTitle;
        private TextView textEventAddress;
        private TextView textEventUrl;

        public EventViewHolder(View itemView) {
            super(itemView);
            imageEvent = itemView.findViewById(R.id.imageEvent);
            textEventTitle = itemView.findViewById(R.id.textEventTitle);
            textEventAddress = itemView.findViewById(R.id.textEventAddress);
            textEventUrl = itemView.findViewById(R.id.textEventUrl);
        }
    }
}
