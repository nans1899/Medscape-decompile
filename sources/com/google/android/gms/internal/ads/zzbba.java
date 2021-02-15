package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import java.util.concurrent.Callable;
import net.media.android.bidder.base.models.internal.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbba implements Callable<String> {
    private final /* synthetic */ Context val$context;

    zzbba(zzbbb zzbbb, Context context) {
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
        String string = sharedPreferences.getString(Logger.USER_AGENT, "");
        if (TextUtils.isEmpty(string)) {
            zzayp.zzei("User agent is not initialized on Google Play Services. Initializing.");
            String defaultUserAgent = WebSettings.getDefaultUserAgent(this.val$context);
            SharedPreferencesUtils.publishWorldReadableSharedPreferences(this.val$context, sharedPreferences.edit().putString(Logger.USER_AGENT, defaultUserAgent), "admob_user_agent");
            return defaultUserAgent;
        }
        zzayp.zzei("User agent is already initialized on Google Play Services.");
        return string;
    }
}
