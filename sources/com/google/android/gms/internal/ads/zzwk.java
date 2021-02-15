package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwk extends zzwn<zzaud> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzanb zzcii;
    private final /* synthetic */ zzvx zzcij;

    zzwk(zzvx zzvx, Context context, zzanb zzanb) {
        this.zzcij = zzvx;
        this.val$context = context;
        this.zzcii = zzanb;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, "rewarded_video");
        return new zzaaa();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcio.zzc(this.val$context, this.zzcii);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zza(ObjectWrapper.wrap(this.val$context), this.zzcii, 202510000);
    }
}
