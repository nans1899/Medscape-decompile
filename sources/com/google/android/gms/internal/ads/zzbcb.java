package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcb implements ThreadFactory {
    private final /* synthetic */ String zzeew;
    private final AtomicInteger zzzm = new AtomicInteger(1);

    zzbcb(String str) {
        this.zzeew = str;
    }

    public final Thread newThread(Runnable runnable) {
        String str = this.zzeew;
        int andIncrement = this.zzzm.getAndIncrement();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23);
        sb.append("AdWorker(");
        sb.append(str);
        sb.append(") #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
