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

import com.example.datadiary.model.DiaryEntry;

public class MainActivity extends AppCompatActivity {

    // UI ELEMENTS
    EditText editTitle, editDate, editMood, editContent;
    Button addButton, editButton, deleteButton;
    TextView textViewResult;

    // DATABASE INSTANCE
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

        // CREATE OR OPEN DATABASE AND TABLE
        database = openOrCreateDatabase("diaryDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS diary(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "content_title VARCHAR, " +
                "due_date VARCHAR, " +
                "mood VARCHAR, " +
                "content VARCHAR)");

        // REFERENCE UI ELEMENTS
        editTitle = findViewById(R.id.editTitle);
        editDate = findViewById(R.id.editDate);
        editMood = findViewById(R.id.editMood);
        editContent = findViewById(R.id.editContent);
        addButton = findViewById(R.id.addButton);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        textViewResult = findViewById(R.id.textViewResult);


        // ADD CONTENT LOGIC
        addButton.setOnClickListener(v -> {
            try {

                DiaryEntry entry = getEntryFromFields();

                database.execSQL("INSERT INTO diary (content_title, due_date, mood, content) VALUES ('"
                        + entry.getTitle() + "', '"
                        + entry.getDate() + "', '"
                        + entry.getMood() + "', '"
                        + entry.getContent() + "')");

                Toast.makeText(this, "Content added!", Toast.LENGTH_LONG).show();
                editTitle.setText("");
                editDate.setText("");
                editMood.setText("");
                editContent.setText("");
                loadContent();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // EDIT CONTENT LOGIC
        editButton.setOnClickListener(v -> {
            try {

                DiaryEntry entry = getEntryFromFields();

                // UPDATE THE RECORD WITH THE MATCHING TITLE
                database.execSQL("UPDATE diary SET due_date='" + entry.getDate() + "', mood='" + entry.getMood() +
                        "', content='" + entry.getContent() + "' WHERE content_title='" + entry.getTitle() + "'");
                Toast.makeText(this, "Diary updated!", Toast.LENGTH_LONG).show();
                clearInputFields();
                loadContent();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // DELETE CONTENT LOGIC
        deleteButton.setOnClickListener(v -> {
            try {

                DiaryEntry entry = getEntryFromFields();

                database.execSQL("DELETE FROM diary WHERE content_title='" + entry.getTitle() + "'");
                Toast.makeText(this, "Content deleted!", Toast.LENGTH_LONG).show();
                clearInputFields();
                loadContent();
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // INITIAL LOAD OF ALL ENTRIES
        loadContent();

    }

    // LOAD ENTRIES FROM DATABASE
    private void loadContent() {
        Cursor cursor = database.rawQuery("SELECT * FROM diary", null);
        StringBuilder result = new StringBuilder();
        while (cursor.moveToNext()) {

            DiaryEntry entry = new DiaryEntry(
                    cursor.getString(1), // title
                    cursor.getString(2), // date
                    cursor.getString(3), // mood
                    cursor.getString(4)  // content

            );

            result.append(entry.getFormattedEntry());

        }
        cursor.close();
        textViewResult.setText(result.toString());
    }


    // CLEAR INPUT FIELDS AFTER ACTION
    private void clearInputFields() {
        editTitle.setText("");
        editDate.setText("");
        editMood.setText("");
        editContent.setText("");
    }

    // CREATE DIARYENTRY OBJECT FROM USER INPUT FIELDS
    private DiaryEntry getEntryFromFields() {
        return new DiaryEntry(
                editTitle.getText().toString(),
                editDate.getText().toString(),
                editMood.getText().toString(),
                editContent.getText().toString()
        );
    }
}