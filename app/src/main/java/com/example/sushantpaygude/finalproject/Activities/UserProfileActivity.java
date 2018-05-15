package com.example.sushantpaygude.finalproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.sushantpaygude.finalproject.R;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton uploadPicture;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Account");

        uploadPicture = findViewById(R.id.buttonCameraIcon);
        uploadPicture.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonCameraIcon:
                Intent uploadUserPictureIntent = new Intent(this, UserChoosingPhotoActivity.class);
                startActivity(uploadUserPictureIntent);
                break;
        }

    }
}
