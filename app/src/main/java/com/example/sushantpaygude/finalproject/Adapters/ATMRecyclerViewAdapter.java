package com.example.sushantpaygude.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.Activities.MapActivity;
import com.example.sushantpaygude.finalproject.POJOs.ATMs.Location;
import com.example.sushantpaygude.finalproject.POJOs.ATMs.Result;
import com.example.sushantpaygude.finalproject.POJOs.Yelp.YelpRestaurantResponse.Business;
import com.example.sushantpaygude.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sushantpaygude on 5/8/18.
 */

public class ATMRecyclerViewAdapter extends RecyclerView.Adapter<ATMRecyclerViewAdapter.ATMViewHolder> {


    private ArrayList<Result> ATMResponseArrayList = new ArrayList<>();
    private Context mcontext;


    public ATMRecyclerViewAdapter(ArrayList<Result> ATMResponseArrayList, Context context) {
        this.ATMResponseArrayList = ATMResponseArrayList;
        this.mcontext = context;
    }

    @Override
    public ATMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_atms, parent, false);
        return new ATMViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ATMViewHolder holder, int position) {

        final Result result = ATMResponseArrayList.get(position);

        if (result != null) {

            holder.textATMTitle.setText(result.getName());
            Picasso.get()
                    .load(result.getIcon())
                    .into(holder.imageATM);
            holder.textATMAddress.setText(result.getVicinity());
            holder.textATMRating.setText(String.valueOf(result.getRating()));
            holder.buttonATMRoute.setOnClickListener(v -> {
                Intent intent = new Intent(mcontext, MapActivity.class);
                Location location = result.getGeometry().getLocation();
                intent.putExtra("EventLatitude", String.valueOf(location.getLat()));
                intent.putExtra("EventLongitude", String.valueOf(location.getLng()));
                //TO DO: Use Location Service to get Current Location of User
                intent.putExtra("UserLatitude", String.valueOf(39.253366));
                intent.putExtra("UserLongitude", String.valueOf(-76.714099));
                intent.putExtra("Name", result.getName());
                mcontext.startActivity(intent);
            });
        }
        //Log.e("Called","Bind");

    }

    @Override
    public int getItemCount() {
        return ATMResponseArrayList.size();
    }

    public class ATMViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageATM;
        private TextView textATMTitle;
        private TextView textATMAddress;
        private TextView textATMRating;
        private ImageButton buttonATMRoute;

        public ATMViewHolder(View itemView) {
            super(itemView);
            imageATM = itemView.findViewById(R.id.imageATM);
            textATMTitle = itemView.findViewById(R.id.textATMTitle);
            textATMAddress = itemView.findViewById(R.id.textATMAddress);
            textATMRating = itemView.findViewById(R.id.textATMRating);
            buttonATMRoute = itemView.findViewById(R.id.imageButtonLocationPin);
        }
    }

}
