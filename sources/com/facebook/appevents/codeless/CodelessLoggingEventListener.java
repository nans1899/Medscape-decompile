package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import java.lang.ref.WeakReference;

public class CodelessLoggingEventListener {
    /* access modifiers changed from: private */
    public static final String TAG = CodelessLoggingEventListener.class.getCanonicalName();

    public static AutoLoggingAccessibilityDelegate getAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
        return new AutoLoggingAccessibilityDelegate(eventBinding, view, view2);
    }

    public static class AutoLoggingAccessibilityDelegate extends View.AccessibilityDelegate {
        private int accessibilityEventType;
        private View.AccessibilityDelegate existingDelegate;
        private WeakReference<View> hostView;
        private EventBinding mapping;
        private WeakReference<View> rootView;
        protected boolean supportButtonIndexing = false;
        private boolean supportCodelessLogging = false;

        public AutoLoggingAccessibilityDelegate() {
        }

        public AutoLoggingAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
            if (eventBinding != null && view != null && view2 != null) {
                this.existingDelegate = ViewHierarchy.getExistingDelegate(view2);
                this.mapping = eventBinding;
                this.hostView = new WeakReference<>(view2);
                this.rootView = new WeakReference<>(view);
                EventBinding.ActionType type = eventBinding.getType();
                int i = AnonymousClass1.$SwitchMap$com$facebook$appevents$codeless$internal$EventBinding$ActionType[eventBinding.getType().ordinal()];
                if (i == 1) {
                    this.accessibilityEventType = 1;
                } else if (i == 2) {
                    this.accessibilityEventType = 4;
                } else if (i == 3) {
                    this.accessibilityEventType = 16;
                } else {
                    throw new FacebookException("Unsupported action type: " + type.toString());
                }
                this.supportCodelessLogging = true;
            }
        }

        public void sendAccessibilityEvent(View view, int i) {
            if (i == -1) {
                Log.e(CodelessLoggingEventListener.TAG, "Unsupported action type");
            }
            if (i == this.accessibilityEventType) {
                View.AccessibilityDelegate accessibilityDelegate = this.existingDelegate;
                if (accessibilityDelegate != null && !(accessibilityDelegate instanceof AutoLoggingAccessibilityDelegate)) {
                    accessibilityDelegate.sendAccessibilityEvent(view, i);
                }
                logEvent();
            }
        }

        private void logEvent() {
            final String eventName = this.mapping.getEventName();
            final Bundle parameters = CodelessMatcher.getParameters(this.mapping, (View) this.rootView.get(), (View) this.hostView.get());
            if (parameters.containsKey(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)) {
                parameters.putDouble(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, AppEventUtility.normalizePrice(parameters.getString(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)));
            }
            parameters.putString(Constants.IS_CODELESS_EVENT_KEY, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    AppEventsLogger.newLogger(FacebookSdk.getApplicationContext()).logEvent(eventName, parameters);
                }
            });
        }

        public boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }

        public boolean getSupportButtonIndexing() {
            return this.supportButtonIndexing;
        }
    }

    /* renamed from: com.facebook.appevents.codeless.CodelessLoggingEventListener$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$appevents$codeless$internal$EventBinding$ActionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.appevents.codeless.internal.EventBinding$ActionType[] r0 = com.facebook.appevents.codeless.internal.EventBinding.ActionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$appevents$codeless$internal$EventBinding$ActionType = r0
                com.facebook.appevents.codeless.internal.EventBinding$ActionType r1 = com.facebook.appevents.codeless.internal.EventBinding.ActionType.CLICK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$appevents$codeless$internal$EventBinding$ActionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.appevents.codeless.internal.EventBinding$ActionType r1 = com.facebook.appevents.codeless.internal.EventBinding.ActionType.SELECTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$appevents$codeless$internal$EventBinding$ActionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.appevents.codeless.internal.EventBinding$ActionType r1 = com.facebook.appevents.codeless.internal.EventBinding.ActionType.TEXT_CHANGED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessLoggingEventListener.AnonymousClass1.<clinit>():void");
        }
    }
}
