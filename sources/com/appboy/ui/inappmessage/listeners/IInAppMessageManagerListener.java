package com.appboy.ui.inappmessage.listeners;

import com.appboy.models.IInAppMessage;
import com.appboy.models.MessageButton;
import com.appboy.ui.inappmessage.InAppMessageCloser;
import com.appboy.ui.inappmessage.InAppMessageOperation;

public interface IInAppMessageManagerListener {
    InAppMessageOperation beforeInAppMessageDisplayed(IInAppMessage iInAppMessage);

    boolean onInAppMessageButtonClicked(MessageButton messageButton, InAppMessageCloser inAppMessageCloser);

    boolean onInAppMessageClicked(IInAppMessage iInAppMessage, InAppMessageCloser inAppMessageCloser);

    void onInAppMessageDismissed(IInAppMessage iInAppMessage);

    @Deprecated
    boolean onInAppMessageReceived(IInAppMessage iInAppMessage);
}
