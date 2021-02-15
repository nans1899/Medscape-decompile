package com.medscape.android.contentviewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import java.util.ArrayList;

public class ContentSectionDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int VIEW_TYPE_CONTENT = 0;
    protected static final int VIEW_TYPE_HEADER = 1;
    protected static final int VIEW_TYPE_SUBHEADER = 2;
    private boolean isNightMode = false;
    private boolean isTextSelectable = true;
    protected String mCurrentSectionTitle;
    protected DataViewHolder.DataListClickListener mDataListClickListener;
    protected ArrayList<LineItem> mItems;
    private int mTextSizeIndex = -1;
    private boolean showArrow = false;

    public long getItemId(int i) {
        return (long) i;
    }

    public ContentSectionDataAdapter() {
    }

    public ContentSectionDataAdapter(ArrayList<LineItem> arrayList) {
        this.mItems = arrayList;
    }

    public int getItemViewType(int i) {
        if (this.mItems.get(i).isHeader) {
            return 1;
        }
        return this.mItems.get(i).isSubsection ? 2 : 0;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            view = from.inflate(R.layout.content_section_header_item, viewGroup, false);
        } else if (i == 2) {
            view = from.inflate(R.layout.content_section_sub_header_item, viewGroup, false);
        } else {
            view = from.inflate(R.layout.text_line_item_references, viewGroup, false);
        }
        return new DataViewHolder(view, this.mDataListClickListener);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        LineItem lineItem = this.mItems.get(i);
        View view = viewHolder.itemView;
        DataViewHolder dataViewHolder = (DataViewHolder) viewHolder;
        dataViewHolder.setNightModeOn(this.isNightMode);
        dataViewHolder.setTextSizeIndex(this.mTextSizeIndex);
        dataViewHolder.setShowArrow(this.showArrow);
        dataViewHolder.bindItem(lineItem);
        GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
        from.setSlm(LinearSLM.ID);
        from.setFirstPosition(lineItem.sectionFirstPosition);
        view.setLayoutParams(from);
    }

    public int getItemCount() {
        ArrayList<LineItem> arrayList = this.mItems;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public String getCurrentSectionTitle() {
        return this.mCurrentSectionTitle;
    }

    public ArrayList<LineItem> getItems() {
        return this.mItems;
    }

    public void addDataListClickListener(DataViewHolder.DataListClickListener dataListClickListener) {
        this.mDataListClickListener = dataListClickListener;
    }

    public void setData(Object obj) {
        this.mItems = (ArrayList) obj;
    }

    public void setNightMode(boolean z) {
        this.isNightMode = z;
        notifyDataSetChanged();
    }

    public void setTextSizeIndex(int i) {
        this.mTextSizeIndex = i;
        notifyDataSetChanged();
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof DataViewHolder) {
            DataViewHolder dataViewHolder = (DataViewHolder) viewHolder;
            if (this.isTextSelectable) {
                dataViewHolder.enableTextSelection();
            } else if (dataViewHolder.textView != null) {
                dataViewHolder.textView.setFocusable(false);
            }
        }
    }

    public void setTextSelectable(boolean z) {
        this.isTextSelectable = z;
    }

    public void setShowArrow(boolean z) {
        this.showArrow = z;
    }
}
