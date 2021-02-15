package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.view.View;
import com.appboy.models.IInAppMessage;

public interface IInAppMessageViewWrapper {
    void close();

    IInAppMessage getInAppMessage();

    View getInAppMessageView();

    boolean getIsAnimatingClose();

    void open(Activity activity);
}
