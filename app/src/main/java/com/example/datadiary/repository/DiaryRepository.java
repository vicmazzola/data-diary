package com.example.datadiary.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datadiary.dao.DiaryDAO;
import com.example.datadiary.data.DatabaseHelper;
import com.example.datadiary.model.DiaryEntry;
import com.example.datadiary.model.Entry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements the DiaryDAO interface to handle all data operations related to diary entries.
 * This class communicates directly with the SQLite database using SQL commands.
 */
public class DiaryRepository implements DiaryDAO {

    private SQLiteDatabase database;

    // INITIALIZE DATABASE CONNECTION USING THE DATABASE HELPER
    public DiaryRepository(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        this.database = helper.getDatabase();
    }

    @Override
    public void insert(DiaryEntry entry) {
        // INSERT ENTRY INTO DATABASE
        String sql = "INSERT INTO diary (content_title, due_date, mood, content) VALUES (?,?,?,?)";
        database.execSQL(sql, new Object[]{
                entry.getTitle(),
                entry.getDate(),
                entry.getMood(),
                entry.getContent()
        });

    }

    @Override
    public void update(DiaryEntry entry) {
        // TO DO: IMPLEMENT UPDATE LOGIC
    }

    @Override
    public void delete(DiaryEntry entry) {
        // TO DO: IMPLEMENT DELETE LOGIC
    }

    @Override

    public List<Entry> getAll() {
        // FETCH ALL ENTRIES FROM DATABASE
        List<Entry> entries = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM diary", null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("content_title"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("due_date"));
                String mood = cursor.getString(cursor.getColumnIndexOrThrow("mood"));
                String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));

                DiaryEntry entry = new DiaryEntry(title, date, mood, content);
                entries.add(entry);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return entries;

    }

}
