package com.medscape.android.activity.interactions.recycler_views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.interfaces.IRemoveIconListener;
import com.medscape.android.db.model.Drug;
import java.util.ArrayList;
import java.util.List;

public class InteractionDrugAdapter extends RecyclerView.Adapter<InteractionDrugViewHolder> {
    private Context mContext;
    List<Drug> mData = new ArrayList();
    IRemoveIconListener mListener;

    public InteractionDrugAdapter(Context context, IRemoveIconListener iRemoveIconListener) {
        this.mListener = iRemoveIconListener;
        this.mContext = context;
    }

    public InteractionDrugViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InteractionDrugViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.interaction_drug_item, viewGroup, false), this.mContext);
    }

    public void onBindViewHolder(InteractionDrugViewHolder interactionDrugViewHolder, final int i) {
        final Drug drug = this.mData.get(i);
        if (drug != null) {
            interactionDrugViewHolder.bindDrug(drug);
            interactionDrugViewHolder.removeIcon.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (InteractionDrugAdapter.this.mListener != null) {
                        InteractionDrugAdapter.this.mListener.onRemoveDrugIconClicked(drug, i);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public void setData(List<Drug> list) {
        this.mData = list;
    }
}
