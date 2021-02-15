package com.medscape.android.contentviewer.view_holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.reference.view.LocatableLinkMovementMethod;
import com.medscape.android.util.Util;

public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView crossLinkIcon;
    private TextView mHeaderLabel;
    DataListClickListener mListener;
    private int mTextSizeIndex = -1;
    protected boolean nightModeOn;
    public LinearLayout rootView;
    private boolean showArrow = false;
    public TextView textView;
    public TextView textViewDetail;

    public interface DataListClickListener {
        void onDataListItemClicked(int i);
    }

    public DataViewHolder(View view) {
        super(view);
    }

    public DataViewHolder(View view, DataListClickListener dataListClickListener) {
        super(view);
        this.mListener = dataListClickListener;
        this.mHeaderLabel = (TextView) view.findViewById(R.id.text);
        this.textView = (TextView) view.findViewById(R.id.text);
        this.textViewDetail = (TextView) view.findViewById(R.id.textDetail);
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setOnClickListener(this);
            this.textView.setMovementMethod(LocatableLinkMovementMethod.getInstance());
        }
        this.crossLinkIcon = (ImageView) view.findViewById(R.id.crossLinkIcon);
        this.rootView = (LinearLayout) view.findViewById(R.id.root_view);
    }

    public DataViewHolder(View view, DataListClickListener dataListClickListener, int i, boolean z) {
        super(view);
        this.mListener = dataListClickListener;
        this.mHeaderLabel = (TextView) view.findViewById(R.id.text);
        TextView textView2 = (TextView) view.findViewById(R.id.text);
        this.textView = textView2;
        if (textView2 != null) {
            textView2.setOnClickListener(this);
            this.textView.setMovementMethod(LocatableLinkMovementMethod.getInstance());
        }
        this.crossLinkIcon = (ImageView) view.findViewById(R.id.crossLinkIcon);
        this.rootView = (LinearLayout) view.findViewById(R.id.root_view);
        this.nightModeOn = z;
        this.mTextSizeIndex = i;
    }

    public void bindItem(CharSequence charSequence) {
        bindItemTextOriginal(charSequence);
        LinearLayout linearLayout = this.rootView;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(getBackgroundColor());
            this.textView.setTextColor(getTextColor());
        }
    }

    public void bindItemOriginal(LineItem lineItem) {
        bindItemTextOriginal(lineItem.text);
        bindCrossLink(lineItem);
        LinearLayout linearLayout = this.rootView;
        if (linearLayout != null) {
            linearLayout.setPadding((int) Util.dpToPixel(linearLayout.getContext(), lineItem.indentation * 10), 0, 0, 0);
        }
    }

    public void bindItemTextOriginal(CharSequence charSequence) {
        if (charSequence != null) {
            this.textView.setText(charSequence);
        }
    }

    public void bindItem(LineItem lineItem) {
        bindItem(lineItem.text);
        if (this.textView != null) {
            if (lineItem.isHeader) {
                this.textView.setTextSize((float) ContentUtils.getHeaderFontSize(this.mTextSizeIndex));
            } else {
                this.textView.setTextSize((float) ContentUtils.getTextFontSize(this.mTextSizeIndex));
            }
        }
        bindCrossLink(lineItem);
    }

    private void bindCrossLink(LineItem lineItem) {
        CrossLink crossLink = lineItem.crossLink;
        ImageView imageView = this.crossLinkIcon;
        if (imageView != null) {
            imageView.setOnClickListener(this);
            if (crossLink == null) {
                this.crossLinkIcon.setVisibility(8);
            } else if (crossLink.type.equals(CrossLink.Type.CALC)) {
                this.crossLinkIcon.setVisibility(0);
                this.crossLinkIcon.setImageResource(R.drawable.calculator);
            } else if (this.showArrow) {
                this.crossLinkIcon.setVisibility(0);
            }
        }
    }

    public void bindHeaderText(String str) {
        TextView textView2 = this.mHeaderLabel;
        if (textView2 != null) {
            textView2.setText(str);
        }
        LinearLayout linearLayout = this.rootView;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(getBackgroundColor());
            this.mHeaderLabel.setTextColor(getTextColor());
        }
    }

    public String toString() {
        return this.textView.getText().toString();
    }

    public void onClick(View view) {
        if (this.mListener != null && getAdapterPosition() != -1) {
            this.mListener.onDataListItemClicked(getAdapterPosition());
        }
    }

    public void setNightModeOn(boolean z) {
        this.nightModeOn = z;
    }

    public void setTextSizeIndex(int i) {
        this.mTextSizeIndex = i;
    }

    /* access modifiers changed from: protected */
    public int getTextSizeIndex() {
        return this.mTextSizeIndex;
    }

    public int getTextColor() {
        if (this.nightModeOn) {
            return -1;
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundColor() {
        if (this.nightModeOn) {
            return ViewCompat.MEASURED_STATE_MASK;
        }
        return -1;
    }

    public void enableTextSelection() {
        TextView textView2 = this.textView;
        if (textView2 != null) {
            textView2.setEnabled(false);
            this.textView.setEnabled(true);
        }
        TextView textView3 = this.mHeaderLabel;
        if (textView3 != null) {
            textView3.setEnabled(false);
            this.mHeaderLabel.setEnabled(true);
        }
        TextView textView4 = this.textViewDetail;
        if (textView4 != null) {
            textView4.setEnabled(false);
            this.textViewDetail.setEnabled(true);
        }
    }

    public void setShowArrow(boolean z) {
        this.showArrow = z;
    }
}
