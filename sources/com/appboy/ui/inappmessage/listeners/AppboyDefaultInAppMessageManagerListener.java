package com.appboy.ui.inappmessage.listeners;

import com.appboy.models.IInAppMessage;
import com.appboy.models.MessageButton;
import com.appboy.ui.inappmessage.InAppMessageCloser;
import com.appboy.ui.inappmessage.InAppMessageOperation;

public class AppboyDefaultInAppMessageManagerListener implements IInAppMessageManagerListener {
    public boolean onInAppMessageButtonClicked(MessageButton messageButton, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public boolean onInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser) {
        return false;
    }

    public void onInAppMessageDismissed(IInAppMessage iInAppMessage) {
    }

    @Deprecated
    public boolean onInAppMessageReceived(IInAppMessage iInAppMessage) {
        return false;
    }

    public InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage iInAppMessage) {
        return InAppMessageOperation.DISPLAY_NOW;
    }
}
