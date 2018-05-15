package com.example.sushantpaygude.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sushantpaygude.finalproject.POJOs.ServerPojo.ServerRegistration;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.Utilities;
import com.example.sushantpaygude.finalproject.Utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, userEmail, userPassword;
    private ImageButton getStartedButton, loginButton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        userName = findViewById(R.id.editTextName);
        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.editTextPassword);
        getStartedButton = findViewById(R.id.buttonGetStarted);
        loginButton = findViewById(R.id.loginButton);

        getStartedButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonGetStarted:
//                Intent navigationDrawerIntent = new Intent(this, NavigationDrawerActivity.class);
                registerNewUser();
//                startActivity(navigationDrawerIntent);
                break;
            case R.id.loginButton:
                Intent signInIntent = new Intent(this, SignInActivity.class);
                startActivity(signInIntent);
                break;
        }
    }


    private void registerNewUser() {

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, Utilities.REGISTRATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ServerRegistration serverRegistration = new Gson().fromJson(response, ServerRegistration.class);
                if (serverRegistration.getUsers().getSuccess() == 1) {
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", userName.getText().toString());
                params.put("password", userPassword.getText().toString());
                params.put("emailid", userEmail.getText().toString());
                params.put("action", "register");
                return params;
                //return super.getParams();
            }
        };


        requestQueue.add(jsonObjectRequest);
    }
}
