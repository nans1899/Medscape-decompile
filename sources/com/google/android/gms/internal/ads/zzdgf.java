package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgf implements zzdgu<Bundle> {
    private final String zzdxp;
    private final String zzgxn;
    private final String zzgxo;
    private final String zzgxp;
    private final Long zzgxq;

    public zzdgf(String str, String str2, String str3, String str4, Long l) {
        this.zzdxp = str;
        this.zzgxn = str2;
        this.zzgxo = str3;
        this.zzgxp = str4;
        this.zzgxq = l;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        zzdot.zza(bundle, "gmp_app_id", this.zzdxp);
        zzdot.zza(bundle, "fbs_aiid", this.zzgxn);
        zzdot.zza(bundle, "fbs_aeid", this.zzgxo);
        zzdot.zza(bundle, "apm_id_origin", this.zzgxp);
        Long l = this.zzgxq;
        if (l != null) {
            bundle.putLong("sai_timeout", l.longValue());
        }
    }
}
