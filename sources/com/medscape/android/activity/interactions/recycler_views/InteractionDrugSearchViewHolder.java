package com.medscape.android.activity.interactions.recycler_views;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.db.model.Drug;

public class InteractionDrugSearchViewHolder extends RecyclerView.ViewHolder {
    TextView drugName;
    protected LinearLayout rowLayout;

    public InteractionDrugSearchViewHolder(View view) {
        super(view);
        this.drugName = (TextView) view.findViewById(R.id.drug_interactions_searched_drug_name);
        this.rowLayout = (LinearLayout) view.findViewById(R.id.drug_interaction_search_row);
    }

    public void bind(Drug drug) {
        this.drugName.setText(drug.getDrugName());
    }
}
