package com.example.datadiary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datadiary.R;
import com.example.datadiary.model.DiaryEntry;
import com.example.datadiary.model.Entry;

import java.util.List;


/**
 * Adapter class for binding Entry data to a RecyclerView.
 *
 * Handles the creation and binding of EntryViewHolder objects that display
 * each diary entry's title, date, mood, and content in the list.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private List<Entry> entryList;

    public EntryAdapter(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    /**
     * Holds views for a single entry item
     */
    public static class EntryViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, itemDate, itemMood, itemContent;


        public EntryViewHolder(View itemView) {
            super(itemView);

            // BIND VIEWS
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDate = itemView.findViewById(R.id.itemDate);
            itemMood = itemView.findViewById(R.id.itemMood);
            itemContent = itemView.findViewById(R.id.itemContent);


        }


    }

    /**
     * Creates a new ViewHolder when the RecyclerView needs one.
     *
     * @param parent   The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new View.
     * @return A new instance of EntryViewHolder.
     */
    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // INFLATE THE XML LAYOUT FOR A SINGLE ITEM
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry, parent, false);

        // RETURN A NEW VIEW HOLDER
        return new EntryViewHolder(view);
    }

    // onBindViewHolder and getItemCount still need to be added

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        // GET ENTRY FROM CURRENT POSITION
        Entry entry = entryList.get(position);

        holder.itemTitle.setText(entry.getTitle());
        holder.itemDate.setText(entry.getDate());
        holder.itemMood.setText(((DiaryEntry) entry).getMood());
        holder.itemContent.setText(entry.getContent());

    }

    @Override
    public int getItemCount() {
        // RETURN THE TOTAL NUMBER OF ITEMS IN THE LIST
        return entryList != null ? entryList.size() : 0;


    }

}
