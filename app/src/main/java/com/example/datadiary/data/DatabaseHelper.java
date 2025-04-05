package com.example.datadiary.data;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Handles database creation and connection for the Data Diary app.
 * Creates the 'diary' table if it does not exist.
 */

public class DatabaseHelper {
    private Context context;
    private SQLiteDatabase database;

    /**
     * Constructs a new DatabaseHelper instance and initializes the database.
     *
     * @param context the context used to open or create the database
     */
    public DatabaseHelper(Context context)
    {
        // OPEN OR CREATE THE DATABASE
        this.database = context.openOrCreateDatabase("diaryDB", MODE_PRIVATE, null);

        // CREATE DIARY TABLE IF IT DOESN'T EXIST
        this.database.execSQL("CREATE TABLE IF NOT EXISTS diary(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "content_title VARCHAR, " +
                "due_date VARCHAR, " +
                "mood VARCHAR, " +
                "content VARCHAR)"
        );

    }


}
