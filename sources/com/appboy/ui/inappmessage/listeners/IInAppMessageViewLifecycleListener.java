package com.appboy.ui.inappmessage.listeners;

import android.view.View;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageImmersive;
import com.appboy.models.MessageButton;
import com.appboy.ui.inappmessage.InAppMessageCloser;

public interface IInAppMessageViewLifecycleListener {
    void afterClosed(IInAppMessage iInAppMessage);

    void afterOpened(View view, IInAppMessage iInAppMessage);

    void beforeClosed(View view, IInAppMessage iInAppMessage);

    void beforeOpened(View view, IInAppMessage iInAppMessage);

    void onButtonClicked(InAppMessageCloser inAppMessageCloser, MessageButton messageButton, IInAppMessageImmersive iInAppMessageImmersive);

    void onClicked(InAppMessageCloser inAppMessageCloser, View view, IInAppMessage iInAppMessage);

    void onDismissed(View view, IInAppMessage iInAppMessage);
}
