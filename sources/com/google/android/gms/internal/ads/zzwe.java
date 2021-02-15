package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwe extends zzwn<zzxc> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzvn zzcig;
    private final /* synthetic */ String zzcih;
    private final /* synthetic */ zzvx zzcij;

    zzwe(zzvx zzvx, Context context, zzvn zzvn, String str) {
        this.zzcij = zzvx;
        this.val$context = context;
        this.zzcig = zzvn;
        this.zzcih = str;
    }

    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, "search");
        return new zzzs();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcik.zza(this.val$context, this.zzcig, this.zzcih, (zzanb) null, 3);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zza(ObjectWrapper.wrap(this.val$context), this.zzcig, this.zzcih, 202510000);
    }
}
