package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwj extends zzwn<zzxs> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzvx zzcij;

    zzwj(zzvx zzvx, Context context) {
        this.zzcij = zzvx;
        this.val$context = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, "mobile_ads_settings");
        return new zzzu();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcim.zzi(this.val$context);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zza(ObjectWrapper.wrap(this.val$context), 202510000);
    }
}
