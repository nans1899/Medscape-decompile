package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;

public class ConsultTextViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextLabel;

    public ConsultTextViewHolder(View view) {
        super(view);
        this.mTextLabel = (TextView) view.findViewById(R.id.empty_list);
    }

    public void bindPost(String str) {
        this.mTextLabel.setText(str);
    }
}
