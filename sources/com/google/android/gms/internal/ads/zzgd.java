package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgd extends zzgm {
    public zzgd(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 51);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzabc) {
            zzey zzey = new zzey((String) this.zzabm.invoke((Object) null, new Object[0]));
            this.zzabc.zzac(zzey.zzzi.longValue());
            this.zzabc.zzad(zzey.zzzj.longValue());
        }
    }
}
