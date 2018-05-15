package com.example.sushantpaygude.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sushantpaygude.finalproject.R;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, userEmail, userPassword;
    private ImageButton getStartedButton, loginButton;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonGetStarted:
                Intent navigationDrawerIntent = new Intent(this, NavigationDrawerActivity.class);
                startActivity(navigationDrawerIntent);
                break;
            case R.id.loginButton:
                Intent signInIntent = new Intent (this, SignInActivity.class);
                startActivity(signInIntent);
                break;
        }
    }
}
