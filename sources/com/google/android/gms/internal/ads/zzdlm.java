package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbtn;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdlm<RequestComponentT extends zzbtn<AdT>, AdT> implements zzdlv<RequestComponentT, AdT> {
    private RequestComponentT zzhbn;
    private final zzdlv<RequestComponentT, AdT> zzhca;

    public zzdlm(zzdlv<RequestComponentT, AdT> zzdlv) {
        this.zzhca = zzdlv;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzasw */
    public final synchronized RequestComponentT zzasv() {
        return this.zzhbn;
    }

    public final synchronized zzdzc<AdT> zza(zzdlw zzdlw, zzdlx<RequestComponentT> zzdlx) {
        if (zzdlw.zzhce != null) {
            RequestComponentT requestcomponentt = (zzbtn) zzdlx.zzc(zzdlw.zzhcf).zzaey();
            this.zzhbn = requestcomponentt;
            return requestcomponentt.zzaev().zzb(zzdlw.zzhce);
        }
        zzdzc<AdT> zza = this.zzhca.zza(zzdlw, zzdlx);
        this.zzhbn = (zzbtn) this.zzhca.zzasv();
        return zza;
    }
}
