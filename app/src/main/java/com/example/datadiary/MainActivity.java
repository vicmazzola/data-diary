package com.example.datadiary;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datadiary.model.DiaryAnalyzer;
import com.example.datadiary.model.DiaryEntry;
import com.example.datadiary.model.Entry;
import com.example.datadiary.repository.DiaryRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // UI ELEMENTS
    EditText editTitle, editDate, editMood, editContent;
    Button addButton, loadButton, editButton, deleteButton;
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
        loadButton = findViewById(R.id.loadButton);
        deleteButton = findViewById(R.id.deleteButton);
        textViewResult = findViewById(R.id.textViewResult);


        // ADD CONTENT LOGIC
        addButton.setOnClickListener(v -> {
            try {

                // VALIDATE IF DATE HAS CORRECT LENGTH AND FORMAT (dd/MM/yyyy)
                String dateInput = editDate.getText().toString().trim();

                if (dateInput.length() != 10 || !dateInput.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    Toast.makeText(this, "Please enter a valid date (dd/MM/yyyy)", Toast.LENGTH_LONG).show();
                    return;
                }

                // CREATE ENTRY OBJECT FROM USER INPUT
                DiaryEntry entry = getEntryFromFields();
                DiaryAnalyzer analyzer = new DiaryAnalyzer();
                analyzer.analyze(this, entry); // this = Context

                // INSERT ENTRY USING REPOSITORY (DATABASE LOGIC IS HANDLED THERE)
                DiaryRepository repo = new DiaryRepository(this);
                repo.insert(entry, () -> {
                    Toast.makeText(this, "✅ Entry added!", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                });


            } catch (Exception e) {
                // DISPLAY ERROR MESSAGE IF SOMETHING GOES WRONG
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //LOAD BUTTON
        loadButton.setOnClickListener(v -> {
            try {
                DiaryRepository repo = new DiaryRepository(this);

                List<Entry> entries = repo.getAll();

                StringBuilder result = new StringBuilder();
                for (Entry entry : entries) {
                    result.append(entry.getFormattedEntry());
                }

                textViewResult.setText(result.toString());

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

        //EDIT DATE LOGIC
        editDate.addTextChangedListener(new TextWatcher() {

            private boolean isFormatting;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) return;
                isFormatting = true;

                // REMOVE SLASHES BEFORE FORMATTING
                String input = s.toString().replace("/", "");
                StringBuilder formatted = new StringBuilder();

                // ADD SLASHES AT POSITIONS 2 AND 5 (FOR dd/MM/yyyy FORMAT)
                for (int i = 0; i < input.length() && i < 8; i++) {
                    formatted.append(input.charAt(i));
                    if ((i == 1 || i == 3) && i != input.length() - 1) {
                        formatted.append("/");
                    }
                }

                // UPDATE THE TEXT FIELD WITHOUT TRIGGERING ANOTHER TEXT CHANGE
                editDate.removeTextChangedListener(this);
                editDate.setText(formatted.toString());
                editDate.setSelection(formatted.length());
                editDate.addTextChangedListener(this);


                // SHOW ALERT IF FORMAT IS INVALID
                if (formatted.length() == 10 && !formatted.toString().matches("\\d{2}/\\d{2}/\\d{4}")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Invalid Date")
                            .setMessage("Expected format: dd/MM/yyyy\nYou entered: " + formatted)
                            .setPositiveButton("OK", null)
                            .show();
                }
                isFormatting = false;
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

    }

    /**
     * Loads all diary entries from the database and displays them in the result TextView.
     */

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


    /**
     * Clears all EditText input fields after add, edit or delete operations.
     */

    // CLEAR INPUT FIELDS AFTER ACTION
    private void clearInputFields() {
        editTitle.setText("");
        editDate.setText("");
        editMood.setText("");
        editContent.setText("");
    }

    /**
     * Returns a DiaryEntry based on EditText input values.
     *
     * @return the entry filled with title, date, mood and content.
     */

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