package com.example.sushantpaygude.finalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.Activities.MapActivity;
import com.example.sushantpaygude.finalproject.POJOs.TicketMaster.EventResponse.Location;
import com.example.sushantpaygude.finalproject.POJOs.Yelp.YelpRestaurantResponse.Business;
import com.example.sushantpaygude.finalproject.R;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sushantpaygude on 4/19/18.
 */

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder> {


    private ArrayList<Business> yelpResponseArrayList = new ArrayList<>();
    private Context mcontext;
    private SharedPreferences shPref;


    public RestaurantRecyclerViewAdapter(ArrayList<Business> yelpResponseArrayList, Context context) {
        this.yelpResponseArrayList = yelpResponseArrayList;
        this.mcontext = context;
        shPref = mcontext.getSharedPreferences("Location", MODE_PRIVATE);
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurants, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        //Log.e("Called","Bind");
        final Business business = yelpResponseArrayList.get(position);

        holder.textRestaurantTitle.setText(business.getName());
        holder.textRestaurantAddress.setText(business.getLocation().getAddress1());
        Picasso.get()
                .load(business.getImageUrl())
                .into(holder.imageRestaurant);
        holder.textRatings.setText(String.valueOf(business.getRating()));
        holder.buttonRestaurantRoute.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, MapActivity.class);
            double latitude = business.getCoordinates().getLatitude();
            double longitude = business.getCoordinates().getLongitude();
            intent.putExtra("EventLatitude", String.valueOf(latitude));
            intent.putExtra("EventLongitude", String.valueOf(longitude));
            //TO DO: Use Location Service to get Current Location of User
//            intent.putExtra("UserLatitude", String.valueOf(39.253366));
//            intent.putExtra("UserLongitude", String.valueOf(-76.714099));
            String lat = shPref.getString("Latitude","");
            String longi = shPref.getString("Longitude","");
            intent.putExtra("UserLatitude", lat);
            intent.putExtra("UserLongitude", longi);
            intent.putExtra("Name", business.getName());
            mcontext.startActivity(intent);
        });

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
        private ImageButton buttonRestaurantRoute;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            imageRestaurant = itemView.findViewById(R.id.imageRestaurant);
            textRestaurantTitle = itemView.findViewById(R.id.textRestaunrantTitle);
            textRestaurantAddress = itemView.findViewById(R.id.textRestaunrantAddress);
            textRatings = itemView.findViewById(R.id.textRatings);
            buttonRestaurantRoute = itemView.findViewById(R.id.imageButtonLocationPin);
        }
    }

}
