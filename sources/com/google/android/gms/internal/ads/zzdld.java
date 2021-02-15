package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbtn;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdld<R extends zzbtn<AdT>, AdT extends zzbqo> implements zzdqv<AdT> {
    private final zzdlv<R, zzdqk<AdT>> zzhbt;

    public zzdld(zzdlv<R, zzdqk<AdT>> zzdlv) {
        this.zzhbt = zzdlv;
    }

    public final void zzb(Throwable th) {
    }

    public final zzdzc<zzdqk<AdT>> zza(zzdqy zzdqy) {
        zzdlc zzdlc = (zzdlc) zzdqy;
        return this.zzhbt.zza(zzdlc.zzhbr, zzdlc.zzhbq);
    }

    public final void zza(zzdqk<AdT> zzdqk) {
        zzdqk.zzhiq = ((zzbtn) this.zzhbt.zzasv()).zzaev();
    }
}
