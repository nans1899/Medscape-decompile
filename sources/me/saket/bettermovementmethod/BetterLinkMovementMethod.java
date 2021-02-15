package me.saket.bettermovementmethod;

import android.app.Activity;
import android.graphics.RectF;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

public class BetterLinkMovementMethod extends LinkMovementMethod {
    private static final int LINKIFY_NONE = -2;
    private static BetterLinkMovementMethod singleInstance;
    private int activeTextViewHashcode;
    private ClickableSpan clickableSpanUnderTouchOnActionDown;
    private boolean isUrlHighlighted;
    private OnLinkClickListener onLinkClickListener;
    private OnLinkLongClickListener onLinkLongClickListener;
    private LongPressTimer ongoingLongPressTimer;
    private final RectF touchedLineBounds = new RectF();
    /* access modifiers changed from: private */
    public boolean wasLongPressRegistered;

    public interface OnLinkClickListener {
        boolean onClick(TextView textView, String str);
    }

    public interface OnLinkLongClickListener {
        boolean onLongClick(TextView textView, String str);
    }

    public static BetterLinkMovementMethod newInstance() {
        return new BetterLinkMovementMethod();
    }

    public static BetterLinkMovementMethod linkify(int i, TextView... textViewArr) {
        BetterLinkMovementMethod newInstance = newInstance();
        for (TextView addLinks : textViewArr) {
            addLinks(i, newInstance, addLinks);
        }
        return newInstance;
    }

    public static BetterLinkMovementMethod linkifyHtml(TextView... textViewArr) {
        return linkify(-2, textViewArr);
    }

    public static BetterLinkMovementMethod linkify(int i, ViewGroup viewGroup) {
        BetterLinkMovementMethod newInstance = newInstance();
        rAddLinks(i, viewGroup, newInstance);
        return newInstance;
    }

    public static BetterLinkMovementMethod linkifyHtml(ViewGroup viewGroup) {
        return linkify(-2, viewGroup);
    }

    public static BetterLinkMovementMethod linkify(int i, Activity activity) {
        BetterLinkMovementMethod newInstance = newInstance();
        rAddLinks(i, (ViewGroup) ((ViewGroup) activity.findViewById(16908290)).getChildAt(0), newInstance);
        return newInstance;
    }

    public static BetterLinkMovementMethod linkifyHtml(Activity activity) {
        return linkify(-2, activity);
    }

    public static BetterLinkMovementMethod getInstance() {
        if (singleInstance == null) {
            singleInstance = new BetterLinkMovementMethod();
        }
        return singleInstance;
    }

    protected BetterLinkMovementMethod() {
    }

    public BetterLinkMovementMethod setOnLinkClickListener(OnLinkClickListener onLinkClickListener2) {
        if (this != singleInstance) {
            this.onLinkClickListener = onLinkClickListener2;
            return this;
        }
        throw new UnsupportedOperationException("Setting a click listener on the instance returned by getInstance() is not supported to avoid memory leaks. Please use newInstance() or any of the linkify() methods instead.");
    }

    public BetterLinkMovementMethod setOnLinkLongClickListener(OnLinkLongClickListener onLinkLongClickListener2) {
        if (this != singleInstance) {
            this.onLinkLongClickListener = onLinkLongClickListener2;
            return this;
        }
        throw new UnsupportedOperationException("Setting a long-click listener on the instance returned by getInstance() is not supported to avoid memory leaks. Please use newInstance() or any of the linkify() methods instead.");
    }

    private static void rAddLinks(int i, ViewGroup viewGroup, BetterLinkMovementMethod betterLinkMovementMethod) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                rAddLinks(i, (ViewGroup) childAt, betterLinkMovementMethod);
            } else if (childAt instanceof TextView) {
                addLinks(i, betterLinkMovementMethod, (TextView) childAt);
            }
        }
    }

    private static void addLinks(int i, BetterLinkMovementMethod betterLinkMovementMethod, TextView textView) {
        textView.setMovementMethod(betterLinkMovementMethod);
        if (i != -2) {
            Linkify.addLinks(textView, i);
        }
    }

    public boolean onTouchEvent(final TextView textView, Spannable spannable, MotionEvent motionEvent) {
        if (this.activeTextViewHashcode != textView.hashCode()) {
            this.activeTextViewHashcode = textView.hashCode();
            textView.setAutoLinkMask(0);
        }
        final ClickableSpan findClickableSpanUnderTouch = findClickableSpanUnderTouch(textView, spannable, motionEvent);
        if (motionEvent.getAction() == 0) {
            this.clickableSpanUnderTouchOnActionDown = findClickableSpanUnderTouch;
        }
        boolean z = this.clickableSpanUnderTouchOnActionDown != null;
        int action = motionEvent.getAction();
        if (action == 0) {
            if (findClickableSpanUnderTouch != null) {
                highlightUrl(textView, findClickableSpanUnderTouch, spannable);
            }
            if (z && this.onLinkLongClickListener != null) {
                startTimerForRegisteringLongClick(textView, new LongPressTimer.OnTimerReachedListener() {
                    public void onTimerReached() {
                        boolean unused = BetterLinkMovementMethod.this.wasLongPressRegistered = true;
                        textView.performHapticFeedback(0);
                        BetterLinkMovementMethod.this.removeUrlHighlightColor(textView);
                        BetterLinkMovementMethod.this.dispatchUrlLongClick(textView, findClickableSpanUnderTouch);
                    }
                });
            }
            return z;
        } else if (action == 1) {
            if (!this.wasLongPressRegistered && z && findClickableSpanUnderTouch == this.clickableSpanUnderTouchOnActionDown) {
                dispatchUrlClick(textView, findClickableSpanUnderTouch);
            }
            cleanupOnTouchUp(textView);
            return z;
        } else if (action == 2) {
            if (findClickableSpanUnderTouch != this.clickableSpanUnderTouchOnActionDown) {
                removeLongPressCallback(textView);
            }
            if (!this.wasLongPressRegistered) {
                if (findClickableSpanUnderTouch != null) {
                    highlightUrl(textView, findClickableSpanUnderTouch, spannable);
                } else {
                    removeUrlHighlightColor(textView);
                }
            }
            return z;
        } else if (action != 3) {
            return false;
        } else {
            cleanupOnTouchUp(textView);
            return false;
        }
    }

    private void cleanupOnTouchUp(TextView textView) {
        this.wasLongPressRegistered = false;
        this.clickableSpanUnderTouchOnActionDown = null;
        removeUrlHighlightColor(textView);
        removeLongPressCallback(textView);
    }

    /* access modifiers changed from: protected */
    public ClickableSpan findClickableSpanUnderTouch(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
        int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
        int scrollX = x + textView.getScrollX();
        int scrollY = y + textView.getScrollY();
        Layout layout = textView.getLayout();
        int lineForVertical = layout.getLineForVertical(scrollY);
        float f = (float) scrollX;
        int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, f);
        this.touchedLineBounds.left = layout.getLineLeft(lineForVertical);
        this.touchedLineBounds.top = (float) layout.getLineTop(lineForVertical);
        this.touchedLineBounds.right = layout.getLineWidth(lineForVertical) + this.touchedLineBounds.left;
        this.touchedLineBounds.bottom = (float) layout.getLineBottom(lineForVertical);
        if (this.touchedLineBounds.contains(f, (float) scrollY)) {
            for (Object obj : spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class)) {
                if (obj instanceof ClickableSpan) {
                    return (ClickableSpan) obj;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void highlightUrl(TextView textView, ClickableSpan clickableSpan, Spannable spannable) {
        if (!this.isUrlHighlighted) {
            this.isUrlHighlighted = true;
            int spanStart = spannable.getSpanStart(clickableSpan);
            int spanEnd = spannable.getSpanEnd(clickableSpan);
            BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(textView.getHighlightColor());
            spannable.setSpan(backgroundColorSpan, spanStart, spanEnd, 18);
            textView.setTag(R.id.bettermovementmethod_highlight_background_span, backgroundColorSpan);
            Selection.setSelection(spannable, spanStart, spanEnd);
        }
    }

    /* access modifiers changed from: protected */
    public void removeUrlHighlightColor(TextView textView) {
        if (this.isUrlHighlighted) {
            this.isUrlHighlighted = false;
            Spannable spannable = (Spannable) textView.getText();
            spannable.removeSpan((BackgroundColorSpan) textView.getTag(R.id.bettermovementmethod_highlight_background_span));
            Selection.removeSelection(spannable);
        }
    }

    /* access modifiers changed from: protected */
    public void startTimerForRegisteringLongClick(TextView textView, LongPressTimer.OnTimerReachedListener onTimerReachedListener) {
        LongPressTimer longPressTimer = new LongPressTimer();
        this.ongoingLongPressTimer = longPressTimer;
        longPressTimer.setOnTimerReachedListener(onTimerReachedListener);
        textView.postDelayed(this.ongoingLongPressTimer, (long) ViewConfiguration.getLongPressTimeout());
    }

    /* access modifiers changed from: protected */
    public void removeLongPressCallback(TextView textView) {
        LongPressTimer longPressTimer = this.ongoingLongPressTimer;
        if (longPressTimer != null) {
            textView.removeCallbacks(longPressTimer);
            this.ongoingLongPressTimer = null;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchUrlClick(TextView textView, ClickableSpan clickableSpan) {
        ClickableSpanWithText ofSpan = ClickableSpanWithText.ofSpan(textView, clickableSpan);
        OnLinkClickListener onLinkClickListener2 = this.onLinkClickListener;
        if (!(onLinkClickListener2 != null && onLinkClickListener2.onClick(textView, ofSpan.text()))) {
            ofSpan.span().onClick(textView);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchUrlLongClick(TextView textView, ClickableSpan clickableSpan) {
        ClickableSpanWithText ofSpan = ClickableSpanWithText.ofSpan(textView, clickableSpan);
        OnLinkLongClickListener onLinkLongClickListener2 = this.onLinkLongClickListener;
        if (!(onLinkLongClickListener2 != null && onLinkLongClickListener2.onLongClick(textView, ofSpan.text()))) {
            ofSpan.span().onClick(textView);
        }
    }

    protected static final class LongPressTimer implements Runnable {
        private OnTimerReachedListener onTimerReachedListener;

        protected interface OnTimerReachedListener {
            void onTimerReached();
        }

        protected LongPressTimer() {
        }

        public void run() {
            this.onTimerReachedListener.onTimerReached();
        }

        public void setOnTimerReachedListener(OnTimerReachedListener onTimerReachedListener2) {
            this.onTimerReachedListener = onTimerReachedListener2;
        }
    }

    protected static class ClickableSpanWithText {
        private ClickableSpan span;
        private String text;

        protected static ClickableSpanWithText ofSpan(TextView textView, ClickableSpan clickableSpan) {
            String str;
            Spanned spanned = (Spanned) textView.getText();
            if (clickableSpan instanceof URLSpan) {
                str = ((URLSpan) clickableSpan).getURL();
            } else {
                str = spanned.subSequence(spanned.getSpanStart(clickableSpan), spanned.getSpanEnd(clickableSpan)).toString();
            }
            return new ClickableSpanWithText(clickableSpan, str);
        }

        protected ClickableSpanWithText(ClickableSpan clickableSpan, String str) {
            this.span = clickableSpan;
            this.text = str;
        }

        /* access modifiers changed from: protected */
        public ClickableSpan span() {
            return this.span;
        }

        /* access modifiers changed from: protected */
        public String text() {
            return this.text;
        }
    }
}
