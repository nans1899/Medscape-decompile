package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzvy extends zzwn<zzaqy> {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzvx zzcij;

    zzvy(zzvx zzvx, Activity activity) {
        this.zzcij = zzvx;
        this.val$activity = activity;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza((Context) this.val$activity, "ad_overlay");
        return null;
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzciq.zzc(this.val$activity);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zzb(ObjectWrapper.wrap(this.val$activity));
    }
}
