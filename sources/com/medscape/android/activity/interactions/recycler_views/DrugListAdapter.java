package com.medscape.android.activity.interactions.recycler_views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.IListSelectionListener;
import com.medscape.android.activity.interactions.interfaces.IRemoveIconListener;
import com.medscape.android.activity.interactions.models.DrugList;
import java.util.ArrayList;
import java.util.List;

public class DrugListAdapter extends RecyclerView.Adapter<DrugListViewHolder> {
    private List<DrugList> mData = new ArrayList();
    /* access modifiers changed from: private */
    public IRemoveIconListener mRemoveListener;
    /* access modifiers changed from: private */
    public IListSelectionListener mSelectionListener;

    public DrugListAdapter(IListSelectionListener iListSelectionListener, IRemoveIconListener iRemoveIconListener) {
        this.mSelectionListener = iListSelectionListener;
        this.mRemoveListener = iRemoveIconListener;
    }

    public DrugListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DrugListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drug_interaction_list_item, viewGroup, false));
    }

    public void onBindViewHolder(DrugListViewHolder drugListViewHolder, final int i) {
        final DrugList drugList = this.mData.get(i);
        if (drugList != null) {
            drugListViewHolder.bindList(drugList);
            drugListViewHolder.rootLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (DrugListAdapter.this.mSelectionListener != null) {
                        DrugListAdapter.this.mSelectionListener.onListSelected(drugList);
                    }
                }
            });
            drugListViewHolder.removeIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (DrugListAdapter.this.mRemoveListener != null) {
                        DrugListAdapter.this.mRemoveListener.onRemoveListIconClicked(drugList.getId().intValue(), i);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setData(List<DrugList> list) {
        this.mData.clear();
        this.mData.addAll(list);
        notifyDataSetChanged();
    }
}
