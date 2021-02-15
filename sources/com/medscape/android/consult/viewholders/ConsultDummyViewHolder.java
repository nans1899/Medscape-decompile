package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class ConsultDummyViewHolder extends RecyclerView.ViewHolder {
    TextView emptyListMessage;

    public ConsultDummyViewHolder(View view) {
        super(view);
        this.emptyListMessage = (TextView) view.findViewById(R.id.empty_list);
    }

    public void bindText(Context context, int i) {
        TextView textView = this.emptyListMessage;
        if (textView != null) {
            if (i == 2) {
                textView.setText(context.getResources().getString(R.string.consult_search_no_results));
            } else {
                textView.setText(context.getResources().getString(R.string.consult_profile_no_posts));
            }
            this.emptyListMessage.setVisibility(0);
        }
    }
}
