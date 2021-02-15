package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IEarnCMEClickedListener;
import com.medscape.android.util.StringUtil;

public class ConsultEarnCMEViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public IEarnCMEClickedListener mClickListener;
    private TextView mCreditsAmount;
    private View mRoot;

    public ConsultEarnCMEViewHolder(View view, IEarnCMEClickedListener iEarnCMEClickedListener) {
        super(view);
        this.mCreditsAmount = (TextView) view.findViewById(R.id.total_credits);
        this.mRoot = view.findViewById(R.id.root);
        this.mClickListener = iEarnCMEClickedListener;
    }

    public void bindData(String str, final String str2) {
        TextView textView;
        if (StringUtil.isNotEmpty(str) && (textView = this.mCreditsAmount) != null) {
            textView.setText(str);
        }
        View view = this.mRoot;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ConsultEarnCMEViewHolder.this.mClickListener != null) {
                        ConsultEarnCMEViewHolder.this.mClickListener.onEarnCMEClicked(str2);
                    }
                }
            });
        }
    }
}
