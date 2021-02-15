package com.medscape.android.activity.interactions.recycler_views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.ISearchItemClickListener;
import com.medscape.android.db.model.Drug;
import java.util.ArrayList;
import java.util.List;

public class InteractionDrugSearchAdapter extends RecyclerView.Adapter<InteractionDrugSearchViewHolder> {
    private List<Drug> mData = new ArrayList();
    /* access modifiers changed from: private */
    public ISearchItemClickListener mListener;

    public InteractionDrugSearchAdapter(ISearchItemClickListener iSearchItemClickListener) {
        this.mListener = iSearchItemClickListener;
    }

    public InteractionDrugSearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InteractionDrugSearchViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.interaction_drug_search_item, viewGroup, false));
    }

    public void onBindViewHolder(InteractionDrugSearchViewHolder interactionDrugSearchViewHolder, int i) {
        final Drug drug = this.mData.get(i);
        if (drug != null) {
            interactionDrugSearchViewHolder.bind(drug);
            interactionDrugSearchViewHolder.rowLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (InteractionDrugSearchAdapter.this.mListener != null) {
                        InteractionDrugSearchAdapter.this.mListener.onSearchItemClicked(drug);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setData(List<Drug> list) {
        this.mData.clear();
        this.mData.addAll(list);
    }
}
