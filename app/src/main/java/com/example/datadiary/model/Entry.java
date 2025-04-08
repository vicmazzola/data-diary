package com.example.datadiary.model;

/**
 * Represents a general entry with a title, date, and content.
 * This class serves as a superclass for more specific types of entries,
 * such as diary entries, task entries, etc.
 */
public class Entry {

    /**
     * The title of the entry
     */
    private String title;

    /**
     * The date of the entry (format: dd/MM/yyyy)
     */
    private String date;

    /**
     * The main content or description of the entry
     */
    private String content;

    /**
     * Constructs a new Entry with the given title, date, and content.
     *
     * @param title   the title of the entry
     * @param date    the date of the entry
     * @param content the content of the entry
     */
    public Entry(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }

    /**
     * Returns the title of the entry.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the date of the entry.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the content of the entry.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns a formatted string representation of the entry.
     *
     * @return the formatted entry
     */
    public String getFormattedEntry() {
        return "Title: " + title +
                "\nDate: " + date +
                "\nContent: " + content +
                "\n\n";
    }
}
