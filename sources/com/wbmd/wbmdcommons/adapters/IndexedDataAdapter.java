package com.wbmd.wbmdcommons.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.viewholders.ItemsAtoZViewHolder;

public class IndexedDataAdapter extends FastScrollerAdapter {
    public static final int INDEX_NAME = 2;
    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_HEADER = 1;
    private String mCurrentSectionTitle;
    private ItemsAtoZViewHolder.DataListClickListener mDataListClickListener;
    private IndexedCursorAdapter mIndexedCursorAdapter;

    public IndexedDataAdapter(IndexedCursorAdapter indexedCursorAdapter, ItemsAtoZViewHolder.DataListClickListener dataListClickListener) {
        this.mIndexedCursorAdapter = indexedCursorAdapter;
        this.mDataListClickListener = dataListClickListener;
    }

    public void setData(IndexedCursorAdapter indexedCursorAdapter) {
        this.mIndexedCursorAdapter = indexedCursorAdapter;
    }

    public int getItemViewType(int i) {
        return isStartOfSection(i) ? 1 : 0;
    }

    private boolean isStartOfSection(int i) {
        if (i == 0 || this.mIndexedCursorAdapter.getHeaderId(i) != this.mIndexedCursorAdapter.getHeaderId(i - 1)) {
            return true;
        }
        return false;
    }

    public ItemsAtoZViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            view = from.inflate(R.layout.header_item, viewGroup, false);
            ((TextView) view.findViewById(R.id.text)).setTextColor(ContextCompat.getColor(viewGroup.getContext(), R.color.webmdblue));
        } else {
            view = from.inflate(R.layout.text_item, viewGroup, false);
        }
        return new ItemsAtoZViewHolder(view, this.mDataListClickListener);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemsAtoZViewHolder) {
            ItemsAtoZViewHolder itemsAtoZViewHolder = (ItemsAtoZViewHolder) viewHolder;
            String string = this.mIndexedCursorAdapter.getItem(i).getString(2);
            View view = itemsAtoZViewHolder.itemView;
            if (isStartOfSection(i)) {
                String str = "" + this.mIndexedCursorAdapter.getFirstCharacter(string);
                this.mCurrentSectionTitle = str;
                itemsAtoZViewHolder.bindHeaderText(str);
            } else {
                itemsAtoZViewHolder.bindItem((CharSequence) string);
            }
            GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
            from.setSlm(LinearSLM.ID);
            from.setFirstPosition(this.mIndexedCursorAdapter.getPositionForSection(this.mIndexedCursorAdapter.getSectionForPosition(i)));
            view.setLayoutParams(from);
        }
    }

    public int getItemCount() {
        return this.mIndexedCursorAdapter.getCount();
    }

    public String getCurrentSectionTitle() {
        return this.mCurrentSectionTitle;
    }
}
