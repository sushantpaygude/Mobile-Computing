package com.example.sushantpaygude.finalproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sushantpaygude.finalproject.R;

public class UserChoosingPhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton takePhotoButton, choosePhotoButton, cancelButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;

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
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.imageButtonChoosePhoto:
                break;
            case R.id.imageButtonCancel:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            Intent returnIntent = new Intent();
            returnIntent.putExtra("userImage", imageBitmap);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            //mImageView.setImageBitmap(imageBitmap);
        }
    }
}
