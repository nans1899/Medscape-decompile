package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.StrictMode;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzbay {
    @Deprecated
    public static <T> T zza(Context context, Callable<T> callable) {
        try {
            return zza(callable);
        } catch (Throwable th) {
            zzbbq.zzc("Unexpected exception.", th);
            zzasf.zzt(context).zza(th, "StrictModeUtil.runWithLaxStrictMode");
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    public static <T> T zza(zzdwe<T> zzdwe) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            T t = zzdwe.get();
            StrictMode.setThreadPolicy(threadPolicy);
            return t;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private static <T> T zza(Callable<T> callable) throws Exception {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            T call = callable.call();
            StrictMode.setThreadPolicy(threadPolicy);
            return call;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
            throw th;
        }
    }
}
