package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchs {
    private final Executor zzfoc;
    private final zzcae zzfxz;
    private final zzbnd zzgdy;

    zzchs(Executor executor, zzbnd zzbnd, zzcae zzcae) {
        this.zzfoc = executor;
        this.zzfxz = zzcae;
        this.zzgdy = zzbnd;
    }

    public final void zzl(zzbgj zzbgj) {
        if (zzbgj != null) {
            this.zzfxz.zzv(zzbgj.getView());
            this.zzfxz.zza(new zzchr(zzbgj), this.zzfoc);
            this.zzfxz.zza(new zzchu(zzbgj), this.zzfoc);
            this.zzfxz.zza(this.zzgdy, this.zzfoc);
            this.zzgdy.zzg(zzbgj);
            zzbgj.zza("/trackActiveViewUnit", (zzahq<? super zzbgj>) new zzcht(this));
            zzbgj.zza("/untrackActiveViewUnit", (zzahq<? super zzbgj>) new zzchw(this));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(zzbgj zzbgj, Map map) {
        this.zzgdy.disable();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzbgj zzbgj, Map map) {
        this.zzgdy.enable();
    }
}
