package com.medscape.android.reference.view;

import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

public class LocatableLinkMovementMethod extends LinkMovementMethod {
    private static Object FROM_BELOW = new NoCopySpan.Concrete();
    private static LocatableLinkMovementMethod sInstance;
    private boolean isPressed = false;
    private int locationX;
    private int locationY;

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        try {
            int action = motionEvent.getAction();
            if (action == 1 || action == 0) {
                this.locationX = (int) motionEvent.getRawX();
                this.locationY = (int) motionEvent.getRawY();
            }
            this.isPressed = action != 0;
            if (!(spannable == null || textView == null)) {
                Layout layout = textView.getLayout();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical((((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY()), (float) ((((int) motionEvent.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX()));
                ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
                if (!(clickableSpanArr == null || clickableSpanArr.length <= 0 || clickableSpanArr[0] == null)) {
                    return super.onTouchEvent(textView, spannable, motionEvent);
                }
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public int[] getClickedLocation() {
        return new int[]{this.locationX, this.locationY};
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new LocatableLinkMovementMethod();
        }
        return sInstance;
    }
}
