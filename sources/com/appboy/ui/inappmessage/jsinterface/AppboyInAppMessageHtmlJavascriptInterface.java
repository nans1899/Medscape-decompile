package com.appboy.ui.inappmessage.jsinterface;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appboy.Appboy;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.ads.AdError;
import java.math.BigDecimal;
import org.json.JSONObject;

public class AppboyInAppMessageHtmlJavascriptInterface {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageHtmlJavascriptInterface.class);
    private Context mContext;
    private AppboyInAppMessageHtmlUserJavascriptInterface mUserInterface;

    public AppboyInAppMessageHtmlJavascriptInterface(Context context) {
        this.mContext = context;
        this.mUserInterface = new AppboyInAppMessageHtmlUserJavascriptInterface(context);
    }

    @JavascriptInterface
    public void requestImmediateDataFlush() {
        Appboy.getInstance(this.mContext).requestImmediateDataFlush();
    }

    @JavascriptInterface
    public void logCustomEventWithJSON(String str, String str2) {
        Appboy.getInstance(this.mContext).logCustomEvent(str, parseProperties(str2));
    }

    @JavascriptInterface
    public void logPurchaseWithJSON(String str, double d, String str2, int i, String str3) {
        String str4 = str;
        String str5 = str2;
        Appboy.getInstance(this.mContext).logPurchase(str4, str5, new BigDecimal(Double.toString(d)), i, parseProperties(str3));
    }

    @JavascriptInterface
    public AppboyInAppMessageHtmlUserJavascriptInterface getUser() {
        return this.mUserInterface;
    }

    /* access modifiers changed from: package-private */
    public AppboyProperties parseProperties(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.equals(AdError.UNDEFINED_DOMAIN) || str.equals("null")) {
                return null;
            }
            return new AppboyProperties(new JSONObject(str));
        } catch (Exception e) {
            String str2 = TAG;
            AppboyLogger.e(str2, "Failed to parse properties JSON String: " + str, e);
            return null;
        }
    }
}
