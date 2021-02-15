package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzft extends zzgm {
    private static final Object zzaba = new Object();
    private static volatile Long zzabb;

    public zzft(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 22);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        if (zzabb == null) {
            synchronized (zzaba) {
                if (zzabb == null) {
                    zzabb = (Long) this.zzabm.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzabc) {
            this.zzabc.zzo(zzabb.longValue());
        }
    }
}
