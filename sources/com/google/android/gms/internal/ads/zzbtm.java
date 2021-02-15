package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbtm {
    private final String packageName;
    private final zzbbx zzbot;
    private final PackageInfo zzdqs;
    private final List<String> zzdrc;
    private final String zzdua;
    private final zzayr zzdzr;
    private final zzdsi zzfsl;
    private final ApplicationInfo zzfty;
    private final zzeos<zzdzc<String>> zzftz;
    private final zzdgw<Bundle> zzfua;

    public zzbtm(zzdsi zzdsi, zzbbx zzbbx, ApplicationInfo applicationInfo, String str, List<String> list, PackageInfo packageInfo, zzeos<zzdzc<String>> zzeos, zzayr zzayr, String str2, zzdgw<Bundle> zzdgw) {
        this.zzfsl = zzdsi;
        this.zzbot = zzbbx;
        this.zzfty = applicationInfo;
        this.packageName = str;
        this.zzdrc = list;
        this.zzdqs = packageInfo;
        this.zzftz = zzeos;
        this.zzdzr = zzayr;
        this.zzdua = str2;
        this.zzfua = zzdgw;
    }

    public final zzdzc<Bundle> zzajo() {
        return this.zzfsl.zzu(zzdsf.SIGNALS).zze(this.zzfua.zzt(new Bundle())).zzavs();
    }

    public final zzdzc<zzatc> zzajp() {
        zzdzc<Bundle> zzajo = zzajo();
        return this.zzfsl.zza(zzdsf.REQUEST_PARCEL, (zzdzc<?>[]) new zzdzc[]{zzajo, this.zzftz.get()}).zzb(new zzbtl(this, zzajo)).zzavs();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzatc zzc(zzdzc zzdzc) throws Exception {
        return new zzatc((Bundle) zzdzc.get(), this.zzbot, this.zzfty, this.packageName, this.zzdrc, this.zzdqs, (String) this.zzftz.get().get(), this.zzdzr.zzxi(), this.zzdua, (zzdqg) null, (String) null);
    }
}
