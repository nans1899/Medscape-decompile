package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayo extends zzdvo {
    public zzayo(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        try {
            super.handleMessage(message);
        } catch (Exception e) {
            zzp.zzkv().zza(e, "AdMobHandler.handleMessage");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(Message message) {
        try {
            super.zzb(message);
        } catch (Throwable th) {
            zzp.zzkr();
            zzayu.zza(zzp.zzkv().getApplicationContext(), th);
            throw th;
        }
    }
}
