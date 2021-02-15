package com.facebook.marketing.internal;

import android.util.Log;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.ViewHierarchy;

public class ButtonIndexingEventListener {
    /* access modifiers changed from: private */
    public static final String TAG = ButtonIndexingEventListener.class.getCanonicalName();

    public static ButtonIndexingAccessibilityDelegate getAccessibilityDelegate(View view, String str) {
        return new ButtonIndexingAccessibilityDelegate(view, str);
    }

    public static class ButtonIndexingAccessibilityDelegate extends CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate {
        private View.AccessibilityDelegate existingDelegate;
        private String mapKey;

        public ButtonIndexingAccessibilityDelegate(View view, String str) {
            if (view != null) {
                this.existingDelegate = ViewHierarchy.getExistingDelegate(view);
                this.mapKey = str;
                this.supportButtonIndexing = true;
            }
        }

        public void sendAccessibilityEvent(final View view, int i) {
            if (i == -1) {
                Log.e(ButtonIndexingEventListener.TAG, "Unsupported action type");
            }
            View.AccessibilityDelegate accessibilityDelegate = this.existingDelegate;
            if (accessibilityDelegate != null && !(accessibilityDelegate instanceof ButtonIndexingAccessibilityDelegate)) {
                accessibilityDelegate.sendAccessibilityEvent(view, i);
            }
            final String str = this.mapKey;
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    ButtonIndexingLogger.logIndexing(FacebookSdk.getApplicationId(), view, str, FacebookSdk.getApplicationContext());
                }
            });
        }
    }
}
