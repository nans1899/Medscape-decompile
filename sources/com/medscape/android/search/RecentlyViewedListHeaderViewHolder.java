package com.medscape.android.search;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class RecentlyViewedListHeaderViewHolder extends RecyclerView.ViewHolder {
    TextView tvClearRecentlyViewed;
    TextView tvItem;

    public RecentlyViewedListHeaderViewHolder(View view) {
        super(view);
        this.tvItem = (TextView) view.findViewById(R.id.list_item);
        this.tvClearRecentlyViewed = (TextView) view.findViewById(R.id.clearRecentlyViewed);
    }
}
