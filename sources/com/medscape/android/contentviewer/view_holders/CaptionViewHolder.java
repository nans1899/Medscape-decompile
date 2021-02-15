package com.medscape.android.contentviewer.view_holders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.medscape.android.R;
import com.medscape.android.contentviewer.CaptionRowLineItem;
import com.medscape.android.contentviewer.ContentUtils;

public class CaptionViewHolder extends DataViewHolder implements View.OnClickListener {
    TextView figureCaptionView;
    LinearLayout rootLayout;
    TextView tableCaptionView;

    public CaptionViewHolder(View view) {
        super(view);
        this.tableCaptionView = (TextView) view.findViewById(R.id.table_caption);
        this.figureCaptionView = (TextView) view.findViewById(R.id.figure_caption);
        this.rootLayout = (LinearLayout) view.findViewById(R.id.root_layout);
    }

    public void bindCaption(CaptionRowLineItem captionRowLineItem) {
        int i;
        TextView textView;
        if (captionRowLineItem.isTableCaption) {
            this.tableCaptionView.setVisibility(0);
            this.figureCaptionView.setVisibility(8);
            textView = this.tableCaptionView;
            i = ViewCompat.MEASURED_STATE_MASK;
            if (this.nightModeOn) {
                i = -1;
            }
            textView.setTextSize((float) (ContentUtils.getTextFontSize(getTextSizeIndex()) - 4));
        } else {
            this.tableCaptionView.setVisibility(8);
            this.figureCaptionView.setVisibility(0);
            textView = this.figureCaptionView;
            int i2 = 1275068416;
            if (this.nightModeOn) {
                i2 = -1249295;
            }
            textView.setTextSize((float) (ContentUtils.getTextFontSize(getTextSizeIndex()) - 2));
        }
        textView.setText(captionRowLineItem.text);
        textView.setTextColor(i);
        this.rootLayout.setBackgroundColor(getBackgroundColor());
    }

    public void enableTextSelection() {
        TextView textView = this.tableCaptionView;
        if (textView != null) {
            textView.setEnabled(false);
            this.tableCaptionView.setEnabled(true);
        }
        TextView textView2 = this.figureCaptionView;
        if (textView2 != null) {
            textView2.setEnabled(false);
            this.figureCaptionView.setEnabled(true);
        }
    }
}
