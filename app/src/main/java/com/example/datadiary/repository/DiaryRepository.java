package com.example.datadiary.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datadiary.data.DatabaseHelper;
import com.example.datadiary.model.DiaryEntry;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepository {

    private SQLiteDatabase database;

    public DiaryRepository(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        this.database = helper.getDatabase();
    }

    public void insertEntry(DiaryEntry entry) {

        String sql = "INSERT INTO diary (content_title, due_date, mood, content) VALUES (?,?,?,?)";
        database.execSQL(sql, new Object[]{
                entry.getTitle(),
                entry.getDate(),
                entry.getMood(),
                entry.getContent()
        });
    }

    public List<DiaryEntry> getAllEntries(){
        List<DiaryEntry> entries = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM diary",null);

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
