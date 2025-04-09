package com.example.datadiary.dao;

import com.example.datadiary.model.DiaryEntry;
import com.example.datadiary.model.Entry;
import com.example.datadiary.util.OnEntryAddedListener;

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
    void insert(DiaryEntry entry, OnEntryAddedListener listener);

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
     * Retrieves all entries from the database.
     *
     * @return a list of entries
     */
    List<Entry> getAll();

}
