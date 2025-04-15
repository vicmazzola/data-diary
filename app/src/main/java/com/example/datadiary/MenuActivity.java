package com.example.datadiary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datadiary.dialog.AboutDialog;

public class MenuActivity extends AppCompatActivity {

    // UI ELEMENTS
    ImageButton listButton, cameraButton, searchButton, calendarButton, infoButton, settingsButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // REFERENCE UI ELEMENTS
        listButton = findViewById(R.id.listButton);
        cameraButton = findViewById(R.id.cameraButton);
        searchButton = findViewById(R.id.searchButton);
        calendarButton = findViewById(R.id.calendarButton);
        infoButton = findViewById(R.id.infoButton);
        settingsButton = findViewById(R.id.settingsButton);


        // SET ONCLICK LISTENER FOR LIST BUTTON
        listButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
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

            AboutDialog aboutDialog = new AboutDialog(MenuActivity.this);
            aboutDialog.show();

        });

        // SET ONCLICK LISTENER FOR SETTINGS BUTTON
        settingsButton.setOnClickListener(v -> {
            //TODO IMPLEMENT FUNCTIONALITY
        });

    }

}
