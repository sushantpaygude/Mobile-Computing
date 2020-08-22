package com.example.sushantpaygude.finalproject.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.sushantpaygude.finalproject.R;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageButton uploadPicture;
    private ImageView userPhoto;
    private ImageButton buttonSubmit;
    private RelativeLayout relativeLayout;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Account");

        uploadPicture = findViewById(R.id.buttonCameraIcon);
        uploadPicture.setOnClickListener(this);

        userPhoto = findViewById(R.id.imageViewUserPhoto);
        buttonSubmit = findViewById(R.id.buttonGetStarted);
        buttonSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonCameraIcon:
                Intent uploadUserPictureIntent = new Intent(this, UserChoosingPhotoActivity.class);
                startActivityForResult(uploadUserPictureIntent, REQUEST_IMAGE_CAPTURE);
                break;
            case R.id.buttonGetStarted:
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("userImage");
            userPhoto.setImageBitmap(imageBitmap);
        }
    }
}
