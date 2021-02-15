package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.models.IInAppMessage;

public interface IHtmlInAppMessageActionListener {
    void onCloseClicked(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onCustomEventFired(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onNewsfeedClicked(IInAppMessage iInAppMessage, String str, Bundle bundle);

    boolean onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle);
}
