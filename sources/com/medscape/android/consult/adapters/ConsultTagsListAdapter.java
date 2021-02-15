package com.medscape.android.consult.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.ITagCountListener;
import com.medscape.android.consult.interfaces.ITagSelectedListener;
import com.medscape.android.consult.viewholders.ConsultTagCheckViewHolder;
import com.medscape.android.custom.FastScrollerAdapter;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import java.util.ArrayList;

public class ConsultTagsListAdapter extends FastScrollerAdapter {
    private static final int VIEW_TYPE_CONTENT = 0;
    private static final int VIEW_TYPE_HEADER = 1;
    private String mCurrentSectionTitle;
    private ArrayList<String> mSelectedTags = new ArrayList<>();
    private ITagCountListener mTagCountListener;
    private IndexedTagsCursorAdapter mTagsCursorAdapter;

    public ConsultTagsListAdapter(IndexedTagsCursorAdapter indexedTagsCursorAdapter, ITagCountListener iTagCountListener) {
        this.mTagsCursorAdapter = indexedTagsCursorAdapter;
        this.mTagCountListener = iTagCountListener;
    }

    public void setData(IndexedTagsCursorAdapter indexedTagsCursorAdapter) {
        this.mTagsCursorAdapter = indexedTagsCursorAdapter;
    }

    public int getItemViewType(int i) {
        return isStartOfSection(i) ? 1 : 0;
    }

    private boolean isStartOfSection(int i) {
        if (i == 0 || this.mTagsCursorAdapter.getHeaderId(i) != this.mTagsCursorAdapter.getHeaderId(i - 1)) {
            return true;
        }
        return false;
    }

    public ConsultTagCheckViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            view = from.inflate(R.layout.header_item, viewGroup, false);
        } else {
            view = from.inflate(R.layout.tag_list_item, viewGroup, false);
        }
        return new ConsultTagCheckViewHolder(view, new ITagSelectedListener() {
            public final void onTagSelected(String str) {
                ConsultTagsListAdapter.this.lambda$onCreateViewHolder$0$ConsultTagsListAdapter(str);
            }
        });
    }

    public /* synthetic */ void lambda$onCreateViewHolder$0$ConsultTagsListAdapter(String str) {
        boolean z = true;
        if (this.mSelectedTags.contains(str)) {
            this.mSelectedTags.remove(str);
        } else if (this.mSelectedTags.size() < 10) {
            this.mSelectedTags.add(str);
        } else {
            ITagCountListener iTagCountListener = this.mTagCountListener;
            if (iTagCountListener != null) {
                iTagCountListener.showMaxTagCountMessage();
            }
            z = false;
        }
        if (z) {
            ITagCountListener iTagCountListener2 = this.mTagCountListener;
            if (iTagCountListener2 != null) {
                iTagCountListener2.onTagCountChanged(this.mSelectedTags.size());
            }
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ConsultTagCheckViewHolder) {
            ConsultTagCheckViewHolder consultTagCheckViewHolder = (ConsultTagCheckViewHolder) viewHolder;
            String string = this.mTagsCursorAdapter.getItem(i).getString(2);
            View view = consultTagCheckViewHolder.itemView;
            if (isStartOfSection(i)) {
                String str = "" + this.mTagsCursorAdapter.getFirstCharacter(string);
                this.mCurrentSectionTitle = str;
                consultTagCheckViewHolder.bindHeaderText(str);
            } else {
                consultTagCheckViewHolder.bindPost(string);
                consultTagCheckViewHolder.setChecked(this.mSelectedTags.contains(string));
            }
            GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
            from.setSlm(LinearSLM.ID);
            from.setFirstPosition(this.mTagsCursorAdapter.getPositionForSection(this.mTagsCursorAdapter.getSectionForPosition(i)));
            view.setLayoutParams(from);
        }
    }

    public int getItemCount() {
        return this.mTagsCursorAdapter.getCount();
    }

    public String getCurrentSectionTitle() {
        return this.mCurrentSectionTitle;
    }

    public ArrayList<String> getSelectedTags() {
        return this.mSelectedTags;
    }

    public void setSelectedTags(ArrayList<String> arrayList) {
        this.mSelectedTags = arrayList;
    }
}
