package com.example.sushantpaygude.finalproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.POJOs.Yelp.YelpRestaurantResponse.Business;
import com.example.sushantpaygude.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sushantpaygude on 4/19/18.
 */

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {


    private ArrayList<Business> yelpResponseArrayList = new ArrayList<>();


    public RestaurantRecyclerViewAdapter(ArrayList<Business> yelpResponseArrayList) {
        this.yelpResponseArrayList = yelpResponseArrayList;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurants, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        //Log.e("Called","Bind");
        Business business = yelpResponseArrayList.get(position);

        holder.textRestaurantTitle.setText(business.getName());
        holder.textRestaurantAddress.setText(business.getLocation().getAddress1());
        Picasso.get()
                .load(business.getImageUrl())
                .into(holder.imageRestaurant);
        holder.textRatings.setText(String.valueOf(business.getRating()));


    }

    @Override
    public int getItemCount() {
        return yelpResponseArrayList.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageRestaurant;
        private TextView textRestaurantTitle;
        private TextView textRestaurantAddress;
        private TextView textRatings;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            imageRestaurant = itemView.findViewById(R.id.imageRestaurant);
            textRestaurantTitle = itemView.findViewById(R.id.textRestaunrantTitle);
            textRestaurantAddress = itemView.findViewById(R.id.textRestaunrantAddress);
            textRatings = itemView.findViewById(R.id.textRatings);
        }
    }

}
