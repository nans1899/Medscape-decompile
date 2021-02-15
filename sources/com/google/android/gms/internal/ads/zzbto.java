package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbto implements zzeoy<zzbtm> {
    private final zzeph<zzayr> zzevu;
    private final zzeph<zzdgw<Bundle>> zzeys;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<zzdsi> zzfqv;
    private final zzeph<ApplicationInfo> zzfub;
    private final zzeph<String> zzfuc;
    private final zzeph<List<String>> zzfud;
    private final zzeph<PackageInfo> zzfue;
    private final zzeph<zzdzc<String>> zzfuf;
    private final zzeph<String> zzfug;

    private zzbto(zzeph<zzdsi> zzeph, zzeph<zzbbx> zzeph2, zzeph<ApplicationInfo> zzeph3, zzeph<String> zzeph4, zzeph<List<String>> zzeph5, zzeph<PackageInfo> zzeph6, zzeph<zzdzc<String>> zzeph7, zzeph<zzayr> zzeph8, zzeph<String> zzeph9, zzeph<zzdgw<Bundle>> zzeph10) {
        this.zzfqv = zzeph;
        this.zzfov = zzeph2;
        this.zzfub = zzeph3;
        this.zzfuc = zzeph4;
        this.zzfud = zzeph5;
        this.zzfue = zzeph6;
        this.zzfuf = zzeph7;
        this.zzevu = zzeph8;
        this.zzfug = zzeph9;
        this.zzeys = zzeph10;
    }

    public static zzbto zza(zzeph<zzdsi> zzeph, zzeph<zzbbx> zzeph2, zzeph<ApplicationInfo> zzeph3, zzeph<String> zzeph4, zzeph<List<String>> zzeph5, zzeph<PackageInfo> zzeph6, zzeph<zzdzc<String>> zzeph7, zzeph<zzayr> zzeph8, zzeph<String> zzeph9, zzeph<zzdgw<Bundle>> zzeph10) {
        return new zzbto(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8, zzeph9, zzeph10);
    }

    public final /* synthetic */ Object get() {
        return new zzbtm(this.zzfqv.get(), this.zzfov.get(), this.zzfub.get(), this.zzfuc.get(), this.zzfud.get(), this.zzfue.get(), zzeov.zzat(this.zzfuf), this.zzevu.get(), this.zzfug.get(), this.zzeys.get());
    }
}
