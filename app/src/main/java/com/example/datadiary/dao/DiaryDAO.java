package com.example.datadiary.dao;

import com.example.datadiary.model.DiaryEntry;

import java.util.List;

/**
 * Interface that defines CRUD operations for diary entries.
 * Any class that implements this interface must handle these methods.
 */
public interface DiaryDAO {

    /**
     * Inserts a new diary entry into the database.
     *
     * @param entry the diary entry to insert
     */
    void insert(DiaryEntry entry);

    /**
     * Updates an existing diary entry in the database.
     *
     * @param entry the diary entry to update
     */
    void update(DiaryEntry entry);

    /**
     * Deletes a diary entry from the database.
     *
     * @param entry the diary entry to delete
     */
    void delete(DiaryEntry entry);

    /**
     * Retrieves all diary entries from the database.
     *
     * @return a list of all diary entries
     */
    List<DiaryEntry> getAll();

}
