package com.medscape.android.reference.style;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.medscape.android.R;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.reference.interfaces.CrossLinkListener;

public class CrossLinkClickableSpan extends ClickableSpan {
    private CrossLinkListener crossLinkClickHandler;
    private final CrossLink link;
    private Context mContext;

    public CrossLinkClickableSpan(CrossLink crossLink, Context context) {
        this.link = crossLink;
        this.mContext = context;
        if (context instanceof CrossLinkListener) {
            this.crossLinkClickHandler = (CrossLinkListener) context;
        }
    }

    public CrossLink getLink() {
        return this.link;
    }

    public void onClick(View view) {
        this.crossLinkClickHandler.onLinkClicked((TextView) view, this.link);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        Context context = this.mContext;
        if (context != null) {
            textPaint.setColor(ContextCompat.getColor(context, R.color.linkcolor));
        }
    }
}
