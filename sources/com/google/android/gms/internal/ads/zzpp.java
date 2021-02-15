package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzpp implements ThreadFactory {
    private final /* synthetic */ String zzbkl;

    zzpp(String str) {
        this.zzbkl = str;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.zzbkl);
    }
}
