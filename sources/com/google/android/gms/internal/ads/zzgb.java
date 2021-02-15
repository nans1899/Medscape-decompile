package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgb extends zzgm {
    private final boolean zzabf;

    public zzgb(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 61);
        this.zzabf = zzex.zzcb();
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzabm.invoke((Object) null, new Object[]{this.zzwg.getContext(), Boolean.valueOf(this.zzabf)})).longValue();
        synchronized (this.zzabc) {
            this.zzabc.zzah(longValue);
        }
    }
}
