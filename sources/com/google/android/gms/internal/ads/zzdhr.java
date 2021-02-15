package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhr implements zzdgx<zzdho> {
    private final Executor executor;
    private final PackageInfo zzdqs;
    private final zzaxx zzgyr;
    private final String zzgyu;

    public zzdhr(zzaxx zzaxx, Executor executor2, String str, PackageInfo packageInfo) {
        this.zzgyr = zzaxx;
        this.executor = executor2;
        this.zzgyu = str;
        this.zzdqs = packageInfo;
    }

    public final zzdzc<zzdho> zzarj() {
        return zzdyq.zzb(zzdyq.zzb(this.zzgyr.zza(this.zzgyu, this.zzdqs), zzdhq.zzdwz, this.executor), Throwable.class, new zzdht(this), this.executor);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzg(Throwable th) throws Exception {
        return zzdyq.zzaf(new zzdho(this.zzgyu));
    }
}
