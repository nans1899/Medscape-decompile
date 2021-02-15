package com.medscape.android.consult.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.ITagSelectedListener;

public class ConsultTagCheckViewHolder extends RecyclerView.ViewHolder {
    private View mCheckMark;
    private TextView mHeaderLabel;
    private View mRootView;
    /* access modifiers changed from: private */
    public ITagSelectedListener mTagSelectedListener;
    /* access modifiers changed from: private */
    public TextView mTextLabel;

    public ConsultTagCheckViewHolder(View view, ITagSelectedListener iTagSelectedListener) {
        super(view);
        this.mTextLabel = (TextView) view.findViewById(R.id.tag_text);
        this.mHeaderLabel = (TextView) view.findViewById(R.id.text);
        this.mCheckMark = view.findViewById(R.id.check_mark);
        View findViewById = view.findViewById(R.id.root);
        this.mRootView = findViewById;
        this.mTagSelectedListener = iTagSelectedListener;
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ConsultTagCheckViewHolder.this.mTagSelectedListener != null && ConsultTagCheckViewHolder.this.mTextLabel != null) {
                        ConsultTagCheckViewHolder.this.mTagSelectedListener.onTagSelected(ConsultTagCheckViewHolder.this.mTextLabel.getText().toString());
                    }
                }
            });
        }
    }

    public void bindPost(String str) {
        this.mTextLabel.setText(str);
    }

    public void bindHeaderText(String str) {
        TextView textView = this.mHeaderLabel;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setChecked(boolean z) {
        View view = this.mCheckMark;
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
