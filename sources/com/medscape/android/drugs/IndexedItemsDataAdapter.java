package com.medscape.android.drugs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.custom.FastScrollerAdapter;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;

public class IndexedItemsDataAdapter extends FastScrollerAdapter {
    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_HEADER = 1;
    String mCurrentSectionTitle;
    DataViewHolder.DataListClickListener mListClickListener;
    IndexedListCursorAdapter mListCursorAdapter;

    public IndexedItemsDataAdapter(IndexedListCursorAdapter indexedListCursorAdapter, DataViewHolder.DataListClickListener dataListClickListener) {
        this.mListCursorAdapter = indexedListCursorAdapter;
        this.mListClickListener = dataListClickListener;
    }

    public void setData(IndexedListCursorAdapter indexedListCursorAdapter) {
        this.mListCursorAdapter = indexedListCursorAdapter;
    }

    public int getItemViewType(int i) {
        return isStartOfSection(i) ? 1 : 0;
    }

    private boolean isStartOfSection(int i) {
        if (i == 0 || this.mListCursorAdapter.getHeaderId(i) != this.mListCursorAdapter.getHeaderId(i - 1)) {
            return true;
        }
        return false;
    }

    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            view = from.inflate(R.layout.header_item, viewGroup, false);
            ((TextView) view.findViewById(R.id.text)).setTextColor(ContextCompat.getColor(viewGroup.getContext(), R.color.medscape_blue));
        } else {
            view = from.inflate(R.layout.text_line_item, viewGroup, false);
        }
        return new DataViewHolder(view, this.mListClickListener);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DataViewHolder) {
            DataViewHolder dataViewHolder = (DataViewHolder) viewHolder;
            String string = this.mListCursorAdapter.getItem(i).getString(this.mListCursorAdapter.columnName);
            View view = dataViewHolder.itemView;
            if (isStartOfSection(i)) {
                String str = "" + this.mListCursorAdapter.getFirstCharacter(string);
                this.mCurrentSectionTitle = str;
                dataViewHolder.bindHeaderText(str);
            } else {
                dataViewHolder.bindItem((CharSequence) string);
            }
            GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
            from.setSlm(LinearSLM.ID);
            from.setFirstPosition(this.mListCursorAdapter.getPositionForSection(this.mListCursorAdapter.getSectionForPosition(i)));
            view.setLayoutParams(from);
        }
    }

    public int getItemCount() {
        return this.mListCursorAdapter.getCount();
    }

    public String getCurrentSectionTitle() {
        return this.mCurrentSectionTitle;
    }
}
