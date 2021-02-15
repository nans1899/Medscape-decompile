package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcqg implements zzbws {
    private final zzawo zzbqq;
    private final Context zzvr;

    zzcqg(Context context, zzawo zzawo) {
        this.zzvr = context;
        this.zzbqq = zzawo;
    }

    public final void zzd(zzatc zzatc) {
    }

    public final void zzb(zzdog zzdog) {
        if (!TextUtils.isEmpty(zzdog.zzhew.zzeou.zzdto)) {
            this.zzbqq.zza(this.zzvr, zzdog.zzhev.zzfsk.zzhfa);
            this.zzbqq.zzk(this.zzvr, zzdog.zzhew.zzeou.zzdto);
        }
    }
}
