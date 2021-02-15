package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdiy {
    private final zzatc zzgkg;
    private final int zzgls;

    public zzdiy(zzatc zzatc, int i) {
        this.zzgkg = zzatc;
        this.zzgls = i;
    }

    public final String zzasf() {
        return this.zzgkg.packageName;
    }

    public final String zzasg() {
        return this.zzgkg.zzdty.getString("ms");
    }

    public final PackageInfo zzash() {
        return this.zzgkg.zzdqs;
    }

    public final boolean zzasi() {
        return this.zzgkg.zzdtz;
    }

    public final List<String> zzasj() {
        return this.zzgkg.zzdrc;
    }

    public final ApplicationInfo zzask() {
        return this.zzgkg.applicationInfo;
    }

    public final String zzasl() {
        return this.zzgkg.zzdua;
    }

    public final int zzasm() {
        return this.zzgls;
    }
}
