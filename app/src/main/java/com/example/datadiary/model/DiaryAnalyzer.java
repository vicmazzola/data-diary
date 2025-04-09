package com.example.datadiary.model;


import android.content.Context;
import android.widget.Toast;

/**
 * Analyzes diary entries and provides feedback based on content or mood.
 */
public class DiaryAnalyzer {

    /**
     * Analyzes the given diary entry and prints a message if it's important.
     *
     * @param context the context used to show the Toast message
     * @param entry   the diary entry to analyze
     */
    public void analyze(Context context, DiaryEntry entry) {
        if (entry.getContent().toLowerCase().contains("urgent") || entry.getMood().equalsIgnoreCase("anxious")) {
            Toast.makeText(context, entry.getTitle() + " might be an important or emotional entry.", Toast.LENGTH_LONG).show();
        }
    }


}
