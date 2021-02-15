package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzcbf {
    private final zzbgj zzdgy;
    private final zzccm zzfww;

    public zzcbf(zzccm zzccm) {
        this(zzccm, (zzbgj) null);
    }

    public zzcbf(zzccm zzccm, zzbgj zzbgj) {
        this.zzfww = zzccm;
        this.zzdgy = zzbgj;
    }

    public final zzccm zzakz() {
        return this.zzfww;
    }

    public final zzbgj zzahl() {
        return this.zzdgy;
    }

    public final View zzala() {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj != null) {
            return zzbgj.getWebView();
        }
        return null;
    }

    public final View zzalb() {
        zzbgj zzbgj = this.zzdgy;
        if (zzbgj == null) {
            return null;
        }
        return zzbgj.getWebView();
    }

    public Set<zzcab<zzbuh>> zzb(zzbtd zzbtd) {
        return Collections.singleton(zzcab.zzb(zzbtd, zzbbz.zzeeu));
    }

    public Set<zzcab<zzbzu>> zzc(zzbtd zzbtd) {
        return Collections.singleton(zzcab.zzb(zzbtd, zzbbz.zzeeu));
    }

    public final zzcab<zzbxz> zzb(Executor executor) {
        return new zzcab<>(new zzcbh(this.zzdgy), executor);
    }
}
