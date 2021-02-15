package com.medscape.android.contentviewer.view_holders;

import android.view.View;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.util.DebugSingleton;

public class NextSectionViewHolder extends DataViewHolder {
    /* access modifiers changed from: private */
    public DataViewHolder.DataListClickListener mListener;
    private View rootView;
    private TextView textView;

    public NextSectionViewHolder(View view, DataViewHolder.DataListClickListener dataListClickListener) {
        super(view);
        this.mListener = dataListClickListener;
        this.textView = (TextView) view.findViewById(R.id.next_section_title);
        this.rootView = view.findViewById(R.id.root_view);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NextSectionViewHolder.this.mListener != null && NextSectionViewHolder.this.getAdapterPosition() != -1) {
                    NextSectionViewHolder.this.mListener.onDataListItemClicked(NextSectionViewHolder.this.getAdapterPosition());
                }
            }
        });
    }

    public void bindItem(CharSequence charSequence) {
        bindItemOrginal(charSequence);
        if (charSequence != null) {
            this.textView.setTextSize((float) ContentUtils.getTextFontSize(getTextSizeIndex()));
        }
        View view = this.rootView;
        if (view != null) {
            view.setBackgroundColor(getBackgroundColor());
        }
    }

    public void bindItemOrginal(CharSequence charSequence) {
        if (charSequence != null) {
            if (DebugSingleton.getInstance().isForceLongSectionName) {
                charSequence = "This is very very long section name, you can turn switch back to correct section name by turning force section name from the debug screen off";
            }
            this.textView.setText(charSequence);
        }
    }

    public void bind(CharSequence charSequence) {
        if (charSequence != null) {
            this.textView.setText(charSequence);
        }
    }
}
