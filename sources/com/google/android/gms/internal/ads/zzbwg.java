package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbwg extends zzbyr<zzbwl> implements zzbuz, zzbwb {
    private final zzdnv zzfmx;
    private AtomicBoolean zzfuy = new AtomicBoolean();

    public zzbwg(Set<zzcab<zzbwl>> set, zzdnv zzdnv) {
        super(set);
        this.zzfmx = zzdnv;
    }

    public final void onAdImpression() {
        if (this.zzfmx.zzhdj == 2 || this.zzfmx.zzhdj == 5 || this.zzfmx.zzhdj == 4) {
            zzajy();
        }
    }

    public final void zzajx() {
        if (this.zzfmx.zzhdj == 1) {
            zzajy();
        }
    }

    private final void zzajy() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcxt)).booleanValue() && this.zzfuy.compareAndSet(false, true) && this.zzfmx.zzheg != null && this.zzfmx.zzheg.type == 3) {
            zza(new zzbwj(this));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbwl zzbwl) throws Exception {
        zzbwl.zzb(this.zzfmx.zzheg);
    }
}
