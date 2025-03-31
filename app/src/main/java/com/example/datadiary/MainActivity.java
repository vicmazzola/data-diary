package com.example.datadiary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTitle, editDate, editMood, editContent;
    Button addButton, editButton, deleteButton;
    TextView textViewResult;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create or open database and table
        database = openOrCreateDatabase("diaryDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS diary(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "content_title VARCHAR, " +
                "due_date VARCHAR, " +
                "mood VARCHAR, " +
                "content VARCHAR)");

        // Reference UI elements
        editTitle = findViewById(R.id.editTitle);
        editDate = findViewById(R.id.editDate);
        editMood = findViewById(R.id.editMood);
        editContent = findViewById(R.id.editContent);
        addButton = findViewById(R.id.addButton);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        textViewResult = findViewById(R.id.textViewResult);

        // Add content logic
        addButton.setOnClickListener(v -> {
            try {
                String title = editTitle.getText().toString();
                String date = editDate.getText().toString();
                String mood = editMood.getText().toString();
                String content = editContent.getText().toString();

                database.execSQL("INSERT INTO diary (content_title, due_date, mood, content) VALUES ('"
                        + title + "', '"
                        + date + "', '"
                        + mood + "', '"
                        + content + "')");

                Toast.makeText(this, "Content added!", Toast.LENGTH_LONG).show();
                editTitle.setText("");
                editDate.setText("");
                editMood.setText("");
                editContent.setText("");
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Edit content logic
        editButton.setOnClickListener(v -> {
            try {
                String title = editTitle.getText().toString();
                String date = editDate.getText().toString();
                String mood = editMood.getText().toString();
                String content = editContent.getText().toString();

                // Update the record with the matching title (for demo purposes)
                database.execSQL("UPDATE diary SET due_date='" + date + "', mood='" + mood +
                        "', content='" + content + "' WHERE content_title='" + title + "'");
                Toast.makeText(this, "Diary updated!", Toast.LENGTH_LONG).show();
                clearInputFields();
                loadContent();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Delete content logic
        deleteButton.setOnClickListener(v -> {
            try {
                String title = editTitle.getText().toString();
                // Delete the record with the matching title
                database.execSQL("DELETE FROM diary WHERE content_title='" + title + "'");
                Toast.makeText(this, "Content deleted!", Toast.LENGTH_LONG).show();
                clearInputFields();
                loadContent();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
;
        loadContent();

    }

    private void loadContent() {
        Cursor cursor = database.rawQuery("SELECT * FROM diary", null);
        StringBuilder result = new StringBuilder();
        while (cursor.moveToNext()) {
            result.append("Title: ").append(cursor.getString(1))
                    .append("\nDue Date: ").append(cursor.getString(2))
                    .append("\nMood: ").append(cursor.getString(3))
                    .append("\nContent: ").append(cursor.getString(4))
                    .append("\n\n");
        }
        cursor.close();
        textViewResult.setText(result.toString());
    }


    private void clearInputFields() {
        editTitle.setText("");
        editDate.setText("");
        editMood.setText("");
        editContent.setText("");
    }
}