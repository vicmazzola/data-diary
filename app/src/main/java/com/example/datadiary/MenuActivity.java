package com.example.datadiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    // UI ELEMENTS
    ImageButton listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // REFERENCE UI ELEMENTS
        ImageButton listButton = findViewById(R.id.listButton);
        ImageButton cameraButton = findViewById(R.id.cameraButton);
        ImageButton calendarButton = findViewById(R.id.calendarButton);
        ImageButton listButton = findViewById(R.id.listButton);
        ImageButton infoButton = findViewById(R.id.infoButton);
        ImageButton settingsButton = findViewById(R.id.settingsButton);

        listButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
