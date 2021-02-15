package com.medscape.android.activity.interactions.recycler_views;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.activity.interactions.models.DrugList;

public class DrugListViewHolder extends RecyclerView.ViewHolder {
    TextView listName;
    public ImageView removeIcon;
    public LinearLayout rootLayout;

    public DrugListViewHolder(View view) {
        super(view);
        this.listName = (TextView) view.findViewById(R.id.drug_lists_item_list_name);
        this.removeIcon = (ImageView) view.findViewById(R.id.drug_list_remove_icon);
        this.rootLayout = (LinearLayout) view.findViewById(R.id.drug_lists_item_root_layout);
    }

    public void bindList(DrugList drugList) {
        if (drugList != null) {
            this.listName.setText(drugList.getListName());
        }
    }
}
