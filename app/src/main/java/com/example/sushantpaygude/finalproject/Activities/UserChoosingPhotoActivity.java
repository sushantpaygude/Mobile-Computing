package com.example.sushantpaygude.finalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sushantpaygude.finalproject.R;

public class UserChoosingPhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton takePhotoButton, choosePhotoButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choosing_photo);

        takePhotoButton = findViewById(R.id.imageButtonTakePhoto);
        choosePhotoButton = findViewById(R.id.imageButtonChoosePhoto);
        cancelButton = findViewById(R.id.imageButtonCancel);

        takePhotoButton.setOnClickListener(this);
        choosePhotoButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonTakePhoto:
                break;
            case R.id.imageButtonChoosePhoto:
                break;
            case R.id.imageButtonCancel:
                finish();
                break;
        }
    }
}
