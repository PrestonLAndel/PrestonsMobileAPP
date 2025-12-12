package com.example.prestonsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewImageActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private Button buttonCaptureImage;
    private ImageView imageViewCaptured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        buttonCaptureImage = findViewById(R.id.buttonCaptureImage);
        imageViewCaptured = findViewById(R.id.imageViewCaptured);

        buttonCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try to launch the camera app using an ACTION_IMAGE_CAPTURE intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                try {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // No camera app available on this emulator/device
                    Toast.makeText(
                            ViewImageActivity.this,
                            "No camera app available on this device.",
                            Toast.LENGTH_SHORT
                    ).show();

                    // Fallback: show a placeholder image so the UI still demonstrates the feature
                    imageViewCaptured.setImageResource(R.mipmap.ic_launcher);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageViewCaptured.setImageBitmap(imageBitmap);
            } else {
                // If for some reason no bitmap is returned, show the placeholder
                imageViewCaptured.setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}
