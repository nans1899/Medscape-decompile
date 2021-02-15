package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwf extends zzwn<zzxc> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzvn zzcig;
    private final /* synthetic */ String zzcih;
    private final /* synthetic */ zzanb zzcii;
    private final /* synthetic */ zzvx zzcij;

    zzwf(zzvx zzvx, Context context, zzvn zzvn, String str, zzanb zzanb) {
        this.zzcij = zzvx;
        this.val$context = context;
        this.zzcig = zzvn;
        this.zzcih = str;
        this.zzcii = zzanb;
    }

    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, FirebaseAnalytics.Event.APP_OPEN);
        return new zzzs();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcik.zza(this.val$context, this.zzcig, this.zzcih, this.zzcii, 4);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zzc(ObjectWrapper.wrap(this.val$context), this.zzcig, this.zzcih, this.zzcii, 202510000);
    }
}
