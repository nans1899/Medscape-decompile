package com.medscape.android.reference.style;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.reference.interfaces.CrossLinkListener;
import com.medscape.android.reference.view.LocatableLinkMovementMethod;

public class TitleClickableSpan extends ClickableSpan {
    private CrossLinkListener crossLinkClickHandler;
    private final CrossLink link;
    private boolean mIsPressed;

    public TitleClickableSpan(CrossLink crossLink, Context context) {
        this.link = crossLink;
        if (context instanceof CrossLinkListener) {
            this.crossLinkClickHandler = (CrossLinkListener) context;
        }
    }

    public CrossLink getLink() {
        return this.link;
    }

    public void onClick(View view) {
        TextView textView = (TextView) view;
        this.mIsPressed = ((LocatableLinkMovementMethod) textView.getMovementMethod()).isPressed();
        this.crossLinkClickHandler.onLinkClicked(textView, this.link);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
    }
}
