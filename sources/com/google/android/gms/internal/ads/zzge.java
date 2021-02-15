package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzge extends zzgm {
    private final StackTraceElement[] zzabh;

    public zzge(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(zzex, str, str2, zza, i, 45);
        this.zzabh = stackTraceElementArr;
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        zzcn zzcn;
        if (this.zzabh != null) {
            zzet zzet = new zzet((String) this.zzabm.invoke((Object) null, new Object[]{this.zzabh}));
            synchronized (this.zzabc) {
                this.zzabc.zzab(zzet.zzyn.longValue());
                if (zzet.zzyo.booleanValue()) {
                    zzcf.zza.C0009zza zza = this.zzabc;
                    if (zzet.zzyp.booleanValue()) {
                        zzcn = zzcn.ENUM_FALSE;
                    } else {
                        zzcn = zzcn.ENUM_TRUE;
                    }
                    zza.zzc(zzcn);
                } else {
                    this.zzabc.zzc(zzcn.ENUM_FAILURE);
                }
            }
        }
    }
}
