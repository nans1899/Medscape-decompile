package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzgg extends zzgm {
    private long zzaar;
    private final zzfi zzxn;

    public zzgg(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2, zzfi zzfi) {
        super(zzex, str, str2, zza, i, 53);
        this.zzxn = zzfi;
        if (zzfi != null) {
            this.zzaar = zzfi.zzcu();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        if (this.zzxn != null) {
            this.zzabc.zzae(((Long) this.zzabm.invoke((Object) null, new Object[]{Long.valueOf(this.zzaar)})).longValue());
        }
    }
}
