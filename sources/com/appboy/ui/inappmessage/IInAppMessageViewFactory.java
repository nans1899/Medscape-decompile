package com.appboy.ui.inappmessage;

import android.app.Activity;
import android.view.View;
import com.appboy.models.IInAppMessage;

public interface IInAppMessageViewFactory {
    View createInAppMessageView(Activity activity, IInAppMessage iInAppMessage);
}
