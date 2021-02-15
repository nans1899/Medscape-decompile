package com.appboy.ui.inappmessage;

public class InAppMessageCloser {
    private InAppMessageViewWrapper mInAppMessageViewWrapper;

    public InAppMessageCloser(InAppMessageViewWrapper inAppMessageViewWrapper) {
        this.mInAppMessageViewWrapper = inAppMessageViewWrapper;
    }

    public void close(boolean z) {
        if (z) {
            this.mInAppMessageViewWrapper.getInAppMessage().setAnimateOut(true);
        } else {
            this.mInAppMessageViewWrapper.getInAppMessage().setAnimateOut(false);
        }
        this.mInAppMessageViewWrapper.close();
    }
}
