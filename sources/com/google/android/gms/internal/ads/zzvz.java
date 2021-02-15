package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzvz extends zzwn<zzauy> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcih;
    private final /* synthetic */ zzanb zzcii;
    private final /* synthetic */ zzvx zzcij;

    zzvz(zzvx zzvx, Context context, String str, zzanb zzanb) {
        this.zzcij = zzvx;
        this.val$context = context;
        this.zzcih = str;
        this.zzcii = zzanb;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, "rewarded");
        return new zzzy();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return zzavo.zzd(this.val$context, this.zzcih, this.zzcii);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zzb(ObjectWrapper.wrap(this.val$context), this.zzcih, this.zzcii, 202510000);
    }
}
