package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdff implements zzeoy<zzdey> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<zzayr> zzevu;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<PackageInfo> zzfue;

    public zzdff(zzeph<zzdzb> zzeph, zzeph<zzdok> zzeph2, zzeph<PackageInfo> zzeph3, zzeph<zzayr> zzeph4) {
        this.zzesr = zzeph;
        this.zzfqr = zzeph2;
        this.zzfue = zzeph3;
        this.zzevu = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzdey(this.zzesr.get(), this.zzfqr.get(), this.zzfue.get(), this.zzevu.get());
    }
}
