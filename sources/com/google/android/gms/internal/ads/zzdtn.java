package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.internal.ads.zzdty;
import com.google.android.gms.internal.ads.zzduc;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdtn {
    private final Looper zzhmm;
    private final Context zzvr;

    public zzdtn(Context context, Looper looper) {
        this.zzvr = context;
        this.zzhmm = looper;
    }

    public final void zzhc(String str) {
        new zzdtq(this.zzvr, this.zzhmm, (zzduc) ((zzejz) zzduc.zzawh().zzhf(this.zzvr.getPackageName()).zzb(zzduc.zzb.BLOCKED_IMPRESSION).zza(zzdty.zzawf().zzhe(str).zzb(zzdty.zza.BLOCKED_REASON_BACKGROUND)).zzbgt())).zzawd();
    }
}
