package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwb extends zzwn<zzaxl> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzanb zzcii;

    zzwb(zzvx zzvx, Context context, zzanb zzanb) {
        this.val$context = context;
        this.zzcii = zzanb;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object zzpp() {
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzpr */
    public final zzaxl zzpq() {
        try {
            return ((zzaxm) zzbbt.zza(this.val$context, "com.google.android.gms.ads.DynamiteSignalGeneratorCreatorImpl", zzwa.zzbxx)).zze(ObjectWrapper.wrap(this.val$context), this.zzcii, 202510000);
        } catch (RemoteException | zzbbv | NullPointerException unused) {
            return null;
        }
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zzb(ObjectWrapper.wrap(this.val$context), this.zzcii, 202510000);
    }
}
