package com.cst7335.lab02canfr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.Activity;

import android.Manifest;

import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "PROFILE_ACTIVITY";
    private ImageButton pictureButton;
    private ImageView imgView;
    private ActivityResultLauncher<Intent> myPictureTakerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Camera right ask
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // request Permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 100);
        }

        // initiate
        pictureButton = findViewById(R.id.pictureButton);
        imgView = findViewById(R.id.imgView);

        //  ActivityResultLauncher
        myPictureTakerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getExtras() != null) {
                                Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                                imgView.setImageBitmap(imgBitmap);
                            }
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            Log.i(TAG, "User refused to capture a picture.");
                        }
                    }
                }
        );

        // listener to start camera
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    // start camera Intent
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "In function: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "In function: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "In function: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "In function: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In function: onDestroy");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // if user agree
                Log.i(TAG, "Camera permission granted.");
            } else {
                // if user reject
                Log.i(TAG, "Camera permission denied.");
            }
        }
    }

}
