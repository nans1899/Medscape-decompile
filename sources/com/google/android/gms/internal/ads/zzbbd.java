package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;
import net.media.android.bidder.base.models.internal.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbbd implements Callable<String> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Context zzedo;

    zzbbd(zzbbb zzbbb, Context context, Context context2) {
        this.zzedo = context;
        this.val$context = context2;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.zzedo != null) {
            zzayp.zzei("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.zzedo.getSharedPreferences("admob_user_agent", 0);
        } else {
            zzayp.zzei("Attempting to read user agent from local cache.");
            sharedPreferences = this.val$context.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString(Logger.USER_AGENT, "");
        if (TextUtils.isEmpty(string)) {
            zzayp.zzei("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.val$context);
            if (z) {
                sharedPreferences.edit().putString(Logger.USER_AGENT, string).apply();
                zzayp.zzei("Persisting user agent.");
            }
        }
        return string;
    }
}
