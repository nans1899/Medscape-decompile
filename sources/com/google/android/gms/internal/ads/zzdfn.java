package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfn implements zzeoy<zzdfk> {
    private final zzeph<PackageInfo> zzfue;
    private final zzeph<ApplicationInfo> zzgjm;

    private zzdfn(zzeph<ApplicationInfo> zzeph, zzeph<PackageInfo> zzeph2) {
        this.zzgjm = zzeph;
        this.zzfue = zzeph2;
    }

    public static zzdfn zzbc(zzeph<ApplicationInfo> zzeph, zzeph<PackageInfo> zzeph2) {
        return new zzdfn(zzeph, zzeph2);
    }

    public static zzdfk zza(ApplicationInfo applicationInfo, PackageInfo packageInfo) {
        return new zzdfk(applicationInfo, packageInfo);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzgjm.get(), this.zzfue.get());
    }
}
