package com.example.sushantpaygude.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sushantpaygude.finalproject.Adapters.ATMRecyclerViewAdapter;
import com.example.sushantpaygude.finalproject.Adapters.RestaurantRecyclerViewAdapter;
import com.example.sushantpaygude.finalproject.POJOs.ATMs.ATMResponse;
import com.example.sushantpaygude.finalproject.POJOs.ATMs.Result;
import com.example.sushantpaygude.finalproject.POJOs.Yelp.YelpRestaurantResponse.Business;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.Utilities;
import com.example.sushantpaygude.finalproject.Utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class ATMFragment extends Fragment {

    private RequestQueue requestQueue;
    private RecyclerView atmRecyclerView;
    private ATMRecyclerViewAdapter atmRecyclerViewAdapter;
    private ArrayList<Result> ATMResponseArraylist = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private boolean loadingData = true;
    private String nextPageToken = "";

    public ATMFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atm, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        atmRecyclerView = view.findViewById(R.id.atmRecyclerView);
        //layoutManager = new LinearLayoutManager(getContext());
        //atmRecyclerView.setLayoutManager(layoutManager);
        atmRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(atmRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        atmRecyclerView.addItemDecoration(dividerItemDecoration);


        atmRecyclerViewAdapter = new ATMRecyclerViewAdapter(ATMResponseArraylist);

        atmRecyclerView.setAdapter(atmRecyclerViewAdapter);
        //atmRecyclerView.addOnScrollListener(onScrollListener);
        addOnscrollListener(atmRecyclerView);
        getATMS(nextPageToken);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void getATMS(String pagetoken) {
        String url = String.format(Utilities.GET_ATMS_BY_LOCATION, "39.260501", "-76.699731", Utilities.GOOGLEPLACES_API_KEY, pagetoken);  //TODO Resolve Bug when Lat/Long has 00 in the end

        //String url = "https://api.yelp.com/v3/businesses/search?latitude=39.260700&longitude=-76.699453&radius=10000";
        Log.e("URL", ":" + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                ATMResponse atmResponse = new Gson().fromJson(response.toString(), ATMResponse.class);
                nextPageToken = atmResponse.getNextPageToken();

                for (Result result : atmResponse.getResults()) {
                    ATMResponseArraylist.add(result);
                }

                atmRecyclerViewAdapter.notifyDataSetChanged();
                loadingData = false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    String jsonError = new String(networkResponse.data);
                    Log.e("Error", ":" + jsonError);
                }

            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Utilities.getYELPTokenParams(getContext());
            }
        };

        requestQueue.add(jsonObjectRequest);
    }


    private void addOnscrollListener(RecyclerView recyclerView) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount;
                int requestCount = 0;
                int totalPages;
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if (!loadingData) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            loadingData = true;

                            if (nextPageToken != null) {
                                //requestCount += 1;
                                getATMS(nextPageToken);
                                nextPageToken = "";

                            }
                        }
                    }

                }
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
    }


}