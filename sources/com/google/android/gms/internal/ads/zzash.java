package com.google.android.gms.internal.ads;

import java.lang.Thread;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzash implements Thread.UncaughtExceptionHandler {
    private final /* synthetic */ zzasf zzdqo;
    private final /* synthetic */ Thread.UncaughtExceptionHandler zzdqp;

    zzash(zzasf zzasf, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdqo = zzasf;
        this.zzdqp = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            this.zzdqo.zza(thread, th);
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.zzdqp;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.zzdqp;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
            }
            throw th2;
        }
    }
}
