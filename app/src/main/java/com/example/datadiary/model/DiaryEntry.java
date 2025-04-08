package com.example.datadiary.model;

/**
 * Represents a single diary entry with an additional mood attribute.
 * Inherits common fields and behavior from the Entry superclass.
 */
public class DiaryEntry extends Entry {

    /** Mood of the diary entry */
    private String mood;

    /**
     * Constructs a DiaryEntry with title, date, content, and mood.
     *
     * @param title   the title of the entry
     * @param date    the date of the entry
     * @param mood    the mood of the user
     * @param content the main content of the diary
     */
    public DiaryEntry(String title, String date, String mood, String content) {
        super(title, date, content); // Call the constructor of Entry
        this.mood = mood;
    }

    /**
     * Returns the mood of the diary entry.
     *
     * @return the mood
     */
    public String getMood() {
        return mood;
    }

    /**
     * Returns a formatted string with all entry details, including mood.
     *
     * @return formatted entry string
     */
    @Override
    public String getFormattedEntry() {
        return "Title: " + getCapitalizedTitle()
                + "\nDate: " + getDate()
                + "\nMood: " + mood
                + "\nContent: " + getContent()
                + "\n\n";
    }

    /**
     * Capitalizes the first letter of the title.
     *
     * @return capitalized title
     */
    public String getCapitalizedTitle() {
        String title = getTitle();
        if (title == null || title.isEmpty()) return title;
        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }
}
