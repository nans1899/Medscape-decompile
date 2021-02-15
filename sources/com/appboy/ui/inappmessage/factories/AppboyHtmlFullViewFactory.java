package com.appboy.ui.inappmessage.factories;

import android.app.Activity;
import android.view.ViewGroup;
import com.appboy.models.IInAppMessage;
import com.appboy.models.InAppMessageHtmlFull;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.IInAppMessageViewFactory;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.inappmessage.views.AppboyInAppMessageHtmlFullView;

public class AppboyHtmlFullViewFactory implements IInAppMessageViewFactory {
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public AppboyHtmlFullViewFactory(IInAppMessageWebViewClientListener iInAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = iInAppMessageWebViewClientListener;
    }

    public AppboyInAppMessageHtmlFullView createInAppMessageView(Activity activity, IInAppMessage iInAppMessage) {
        AppboyInAppMessageHtmlFullView appboyInAppMessageHtmlFullView = (AppboyInAppMessageHtmlFullView) activity.getLayoutInflater().inflate(R.layout.com_appboy_inappmessage_html_full, (ViewGroup) null);
        appboyInAppMessageHtmlFullView.setWebViewContent(iInAppMessage.getMessage(), ((InAppMessageHtmlFull) iInAppMessage).getLocalAssetsDirectoryUrl());
        appboyInAppMessageHtmlFullView.setInAppMessageWebViewClient(new InAppMessageWebViewClient(activity.getApplicationContext(), iInAppMessage, this.mInAppMessageWebViewClientListener));
        return appboyInAppMessageHtmlFullView;
    }
}
