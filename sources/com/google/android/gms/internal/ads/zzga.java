package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzga extends zzgm {
    public zzga(zzex zzex, String str, String str2, zzcf.zza.C0009zza zza, int i, int i2) {
        super(zzex, str, str2, zza, i, 3);
    }

    /* access modifiers changed from: protected */
    public final void zzcw() throws IllegalAccessException, InvocationTargetException {
        boolean booleanValue = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcqz)).booleanValue();
        zzek zzek = new zzek((String) this.zzabm.invoke((Object) null, new Object[]{this.zzwg.getContext(), Boolean.valueOf(booleanValue)}));
        synchronized (this.zzabc) {
            this.zzabc.zze(zzek.zzyk);
            this.zzabc.zzag(zzek.zzyl);
        }
    }
}
