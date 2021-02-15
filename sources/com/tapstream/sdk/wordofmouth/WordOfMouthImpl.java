package com.tapstream.sdk.wordofmouth;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapstream.sdk.Platform;
import com.tapstream.sdk.PopupWebView;

public class WordOfMouthImpl implements WordOfMouth {
    final Platform platform;

    public static WordOfMouth getInstance(Platform platform2) {
        return new WordOfMouthImpl(platform2);
    }

    private WordOfMouthImpl(Platform platform2) {
        this.platform = platform2;
    }

    public void consumeReward(Reward reward) {
        this.platform.consumeReward(reward);
    }

    public boolean isConsumed(Reward reward) {
        return this.platform.isConsumed(reward);
    }

    public void showOffer(Activity activity, View view, Offer offer) {
        PopupWebView initializeWithActivity = PopupWebView.initializeWithActivity(activity);
        final String loadSessionId = this.platform.loadSessionId();
        final Offer offer2 = offer;
        final Activity activity2 = activity;
        final PopupWebView popupWebView = initializeWithActivity;
        initializeWithActivity.showPopupWithMarkup(view, offer.getMarkup(), new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.endsWith("accept")) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", offer2.prepareMessage(loadSessionId));
                    intent.setType("text/plain");
                    activity2.startActivity(intent);
                }
                popupWebView.dismiss();
                return true;
            }
        });
    }
}
