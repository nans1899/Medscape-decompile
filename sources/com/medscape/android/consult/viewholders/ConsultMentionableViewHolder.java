package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IMentionSelectedListener;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.util.StringUtil;

public class ConsultMentionableViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public IMentionSelectedListener mListener;
    private View mRootView;
    private TextView mTextLabel;

    public ConsultMentionableViewHolder(View view, IMentionSelectedListener iMentionSelectedListener) {
        super(view);
        this.mTextLabel = (TextView) view.findViewById(R.id.mentionable);
        this.mRootView = view.findViewById(R.id.root);
        this.mListener = iMentionSelectedListener;
    }

    public void bindPost(final ConsultUser consultUser) {
        if (consultUser != null && StringUtil.isNotEmpty(consultUser.getDisplayName())) {
            this.mTextLabel.setText(consultUser.getDisplayName());
            this.mRootView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultMentionableViewHolder.this.mListener.onMentionSelected(consultUser, -1, -1);
                }
            });
        }
    }
}
