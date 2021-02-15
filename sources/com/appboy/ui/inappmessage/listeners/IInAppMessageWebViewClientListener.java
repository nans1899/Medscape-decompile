package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.models.IInAppMessage;

public interface IInAppMessageWebViewClientListener {
    void onCloseAction(IInAppMessage iInAppMessage, String str, Bundle bundle);

    void onCustomEventAction(IInAppMessage iInAppMessage, String str, Bundle bundle);

    void onNewsfeedAction(IInAppMessage iInAppMessage, String str, Bundle bundle);

    void onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle);
}
