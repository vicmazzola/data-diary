package com.example.datadiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button enterButton = findViewById(R.id.startButton);
        enterButton.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
