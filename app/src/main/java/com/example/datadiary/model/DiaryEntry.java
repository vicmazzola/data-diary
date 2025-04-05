package com.example.datadiary.model;


/**
 * Represents a single diary entry containing title, date, mood, and content.
 * Used to encapsulate and format diary data inside the Data Diary app.
 */

public class DiaryEntry {

    //ATTRIBUTES
    private String title;
    private String date;
    private String mood;
    private String content;


    /**
     * Creates a new DiaryEntry with all required fields.
     *
     * @param title   the title of the diary entry
     * @param date    the date of the entry
     * @param mood    the mood of the user
     * @param content the main content of the diary
     */

    // CONSTRUCTORS
    public DiaryEntry(String title, String date, String mood, String content) {
        this.title = title;
        this.date = date;
        this.mood = mood;
        this.content = content;
    }


    //GETTERS
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns a formatted string with all entry details.
     * Useful for displaying entries in the UI.
     *
     * @return formatted entry string
     */
    // FORMAT WHEN GET ENTRY
    public String getFormattedEntry() {
        return "Title: " + title + "\nDate: " + date + "\nMood: " + mood + "\nContent: " + content + "\n\n";
    }


}
