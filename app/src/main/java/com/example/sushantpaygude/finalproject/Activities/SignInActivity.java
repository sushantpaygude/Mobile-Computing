package com.example.sushantpaygude.finalproject.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sushantpaygude.finalproject.POJOs.ServerPojo.ServerRegistration;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.TinyDB;
import com.example.sushantpaygude.finalproject.Utils.Utilities;
import com.example.sushantpaygude.finalproject.Utils.VolleySingleton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton signInButton, newUserButton, loginButton;
    private GoogleSignInClient googleSignInClient;
    private TinyDB tinyDB;
    private EditText userID, userPassword;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        newUserButton = findViewById(R.id.buttonCreateNewUser);
        loginButton = findViewById(R.id.loginButton);
        userID = findViewById(R.id.editTextID);
        userPassword = findViewById(R.id.editTextPassword);


        signInButton.setOnClickListener(this);
        newUserButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

    }

    @Override
    protected void onStart() {


        super.onStart();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                if (account != null) {
                    startDrawerActivity(account);

                } else {
                    Intent signInIntent = googleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, Utilities.RC_SIGN_IN);
                }

                break;
            case R.id.buttonCreateNewUser:
                Intent newUserIntent = new Intent(this, NewUserActivity.class);
                startActivity(newUserIntent);
                break;
            case R.id.loginButton:
                String userID_ = userID.getText().toString();
                String userPassword_ = userPassword.getText().toString();
                loginfromServer();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Utilities.RC_SIGN_IN && resultCode == RESULT_OK) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            startDrawerActivity(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("Error", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }


    private void startDrawerActivity(GoogleSignInAccount googleSignInAccount) {

        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        intent.putExtra(Utilities.GOOGLESIGNINACCOUNT, googleSignInAccount);
        //intent.putExtra(Utilities.GOOGLESIGNINCLIENT, googleSignInClient);
        startActivity(intent);

    }


    private void loginfromServer() {

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, Utilities.REGISTRATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ServerRegistration serverRegistration = new Gson().fromJson(response, ServerRegistration.class);
                if (serverRegistration.getUsers().getSuccess() == 1) {
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    intent.putExtra(Utilities.SERVERSIGNINACCOUNT, serverRegistration);
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
                params.put("username", userID.getText().toString());
                params.put("password", userPassword.getText().toString());
                params.put("action", "login");
                return params;
                //return super.getParams();
            }
        };


        requestQueue.add(jsonObjectRequest);
    }

}
