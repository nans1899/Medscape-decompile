package com.appboy.ui.inappmessage;

import android.view.View;
import java.util.List;

public interface IInAppMessageImmersiveView extends IInAppMessageView {
    List<View> getMessageButtonViews();

    View getMessageCloseButtonView();
}
