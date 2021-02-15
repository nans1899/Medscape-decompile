package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgi extends zzgm {
    public zzgi(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 48);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        this.zzabc.zza(zzcn.ENUM_FAILURE);
        boolean booleanValue = ((Boolean) this.zzabm.invoke((Object) null, new Object[]{this.zzwg.getContext()})).booleanValue();
        synchronized (this.zzabc) {
            if (booleanValue) {
                this.zzabc.zza(zzcn.ENUM_TRUE);
            } else {
                this.zzabc.zza(zzcn.ENUM_FALSE);
            }
        }
    }
}
