package com.tapstream.sdk.landers;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapstream.sdk.Platform;
import com.tapstream.sdk.PopupWebView;

public class InAppLanderImpl {
    final Platform platform;

    public static InAppLanderImpl getInstance(Platform platform2) {
        return new InAppLanderImpl(platform2);
    }

    InAppLanderImpl(Platform platform2) {
        this.platform = platform2;
    }

    public boolean shouldShowLander(Lander lander) {
        return !this.platform.hasShown(lander);
    }

    public void showLander(Activity activity, View view, final Lander lander, final ILanderDelegate iLanderDelegate) {
        final PopupWebView initializeWithActivity = PopupWebView.initializeWithActivity(activity);
        AnonymousClass1 r0 = new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.endsWith("close") && !str.endsWith("close/")) {
                    return false;
                }
                iLanderDelegate.dismissedLander();
                initializeWithActivity.dismiss();
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                if (!str.startsWith("https://tapstream.com") && !str.equals(lander.getUrl())) {
                    initializeWithActivity.dismiss();
                    iLanderDelegate.submittedLander();
                }
            }
        };
        if (lander.getUrl() == null) {
            initializeWithActivity.showPopupWithMarkup(view, lander.getMarkup(), r0);
        } else {
            initializeWithActivity.showPopupWithUrl(view, lander.getUrl(), r0);
        }
        this.platform.registerLanderShown(lander);
        iLanderDelegate.showedLander(lander);
    }
}
