package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbtn;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdln<RequestComponentT extends zzbtn<AdT>, AdT> implements zzdlv<RequestComponentT, AdT> {
    private RequestComponentT zzhbn;

    /* access modifiers changed from: private */
    /* renamed from: zzasw */
    public final synchronized RequestComponentT zzasv() {
        return this.zzhbn;
    }

    public final synchronized zzdzc<AdT> zza(zzdlw zzdlw, zzdlx<RequestComponentT> zzdlx) {
        RequestComponentT requestcomponentt;
        requestcomponentt = (zzbtn) zzdlx.zzc(zzdlw.zzhcf).zzaey();
        this.zzhbn = requestcomponentt;
        return requestcomponentt.zzaev().zzajh();
    }
}
