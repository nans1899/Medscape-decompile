package com.appboy.ui.inappmessage.listeners;

import android.os.Bundle;
import com.appboy.models.IInAppMessage;

public class AppboyDefaultHtmlInAppMessageActionListener implements IHtmlInAppMessageActionListener {
    public void onCloseClicked(IInAppMessage iInAppMessage, String str, Bundle bundle) {
    }

    public boolean onCustomEventFired(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        return false;
    }

    public boolean onNewsfeedClicked(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        return false;
    }

    public boolean onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        return false;
    }
}
