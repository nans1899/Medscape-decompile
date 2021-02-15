package com.wbmd.qxcalculator.views;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import com.wbmd.qxcalculator.R;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class BetterLinkTextView extends TextView {
    private void init(Context context, AttributeSet attributeSet, int i) {
    }

    public BetterLinkTextView(Context context) {
        super(context);
    }

    public BetterLinkTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    public BetterLinkTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Object tag;
        super.onTouchEvent(motionEvent);
        if (!(getMovementMethod() instanceof BetterLinkMovementMethod) || (tag = getTag(R.id.bettermovementmethod_highlight_background_span)) == null || getText() == null || !(getText() instanceof Spannable) || ((Spannable) getText()).getSpanStart(tag) < 0) {
            return false;
        }
        return true;
    }
}
