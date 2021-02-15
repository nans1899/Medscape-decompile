package com.medscape.android.activity.interactions.recycler_views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.db.model.Drug;

public class InteractionDrugViewHolder extends RecyclerView.ViewHolder {
    private TextView brandName;
    private TextView drugName;
    private Context mContext;
    private LinearLayout namesLayout;
    public ImageView removeIcon;

    public InteractionDrugViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.drugName = (TextView) view.findViewById(R.id.drug_interaction_item_name);
        this.removeIcon = (ImageView) view.findViewById(R.id.drug_interaction_remove_icon);
        this.namesLayout = (LinearLayout) view.findViewById(R.id.drug_interaction_names_layout);
    }

    public void bindDrug(Drug drug) {
        if (drug != null) {
            this.drugName.setText(drug.getDrugName());
            if (drug.getDnames() == null || drug.getDnames().size() <= 0) {
                this.namesLayout.setVisibility(8);
            } else {
                this.namesLayout.removeAllViews();
                this.namesLayout.setVisibility(0);
                for (String str : drug.getDnames()) {
                    TextView textView = new TextView(this.mContext);
                    textView.setText("-" + str);
                    this.namesLayout.addView(textView);
                }
            }
        }
        this.removeIcon.setContentDescription(this.mContext.getString(R.string.drug_interaction_remove_icon_content_desc, new Object[]{drug.getDrugName()}));
    }
}
