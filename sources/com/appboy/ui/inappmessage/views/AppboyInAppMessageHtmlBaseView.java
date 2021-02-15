package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.appboy.ui.inappmessage.IInAppMessageView;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;

public abstract class AppboyInAppMessageHtmlBaseView extends RelativeLayout implements IInAppMessageView {
    private static final String FILE_URI_SCHEME_PREFIX = "file://";
    private static final String HTML_ENCODING = "utf-8";
    private static final String HTML_MIME_TYPE = "text/html";

    public View getMessageClickableView() {
        return this;
    }

    public abstract WebView getMessageWebView();

    public AppboyInAppMessageHtmlBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setWebViewContent(String str, String str2) {
        WebView messageWebView = getMessageWebView();
        messageWebView.loadDataWithBaseURL(FILE_URI_SCHEME_PREFIX + str2 + "/", str, HTML_MIME_TYPE, HTML_ENCODING, (String) null);
    }

    public void setInAppMessageWebViewClient(InAppMessageWebViewClient inAppMessageWebViewClient) {
        getMessageWebView().setWebViewClient(inAppMessageWebViewClient);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        InAppMessageViewUtils.closeInAppMessageOnKeycodeBack();
        return true;
    }
}
