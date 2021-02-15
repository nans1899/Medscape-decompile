package com.medscape.android.search;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class SearchListItemViewHolder extends RecyclerView.ViewHolder {
    TextView item;

    public SearchListItemViewHolder(View view) {
        super(view);
        this.item = (TextView) view.findViewById(R.id.list_item);
    }
}
