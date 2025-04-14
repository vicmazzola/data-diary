package com.example.datadiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    // UI ELEMENTS
    ImageButton listButton, cameraButton, searchButton, calendarButton, infoButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // REFERENCE UI ELEMENTS
        ImageButton listButton = findViewById(R.id.listButton);
        ImageButton cameraButton = findViewById(R.id.cameraButton);
        ImageButton searchButton = findViewById(R.id.searchButton);
        ImageButton calendarButton = findViewById(R.id.calendarButton);
        ImageButton infoButton = findViewById(R.id.infoButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);


        // SET ONCLICK LISTENER FOR LIST BUTTON
        listButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // SET ONCLICK LISTENER FOR CAMERA BUTTON
        cameraButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

        // SET ONCLICK LISTENER FOR SEARCH BUTTON
        searchButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

        // SET ONCLICK LISTENER FOR CALENDAR BUTTON
        calendarButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

        // SET ONCLICK LISTENER FOR INFO BUTTON
        infoButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

        // SET ONCLICK LISTENER FOR SETTINGS BUTTON
        settingsButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

    }

}
