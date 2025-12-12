package com.example.prestonsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonExplicit;
    private Button buttonImplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonExplicit = findViewById(R.id.buttonExplicit);
        buttonImplicit = findViewById(R.id.buttonImplicit);

        // Explicit Intent: directly specify SecondActivity
        buttonExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent explicitIntent =
                        new Intent(MainActivity.this, SecondActivity.class);
                startActivity(explicitIntent);
            }
        });

        // Implicit Intent: use a custom action string that SecondActivity listens for
        buttonImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent implicitIntent =
                        new Intent("com.example.prestonsapp.ACTION_SHOW_CHALLENGES");
                startActivity(implicitIntent);
            }
        });
    }
}
