package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzfs extends zzgm {
    private long startTime;

    public zzfs(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, long j, int i, int i2) {
        super(zzex, str, str2, zza, i, 25);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzabm.invoke((Object) null, new Object[0])).longValue();
        synchronized (this.zzabc) {
            this.zzabc.zzak(longValue);
            if (this.startTime != 0) {
                this.zzabc.zzm(longValue - this.startTime);
                this.zzabc.zzp(this.startTime);
            }
        }
    }
}
