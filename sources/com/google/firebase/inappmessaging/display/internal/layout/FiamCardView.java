package com.google.firebase.inappmessaging.display.internal.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.google.firebase.inappmessaging.display.internal.layout.util.BackButtonHandler;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class FiamCardView extends CardView implements BackButtonLayout {
    private BackButtonHandler mBackHandler;

    public FiamCardView(Context context) {
        super(context);
    }

    public FiamCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FiamCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDismissListener(View.OnClickListener onClickListener) {
        this.mBackHandler = new BackButtonHandler(this, onClickListener);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean dispatchKeyEvent = this.mBackHandler.dispatchKeyEvent(keyEvent);
        if (dispatchKeyEvent != null) {
            return dispatchKeyEvent.booleanValue();
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
