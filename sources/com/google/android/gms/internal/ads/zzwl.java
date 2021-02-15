package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwl extends zzwn<zzael> {
    private final /* synthetic */ zzvx zzcij;
    private final /* synthetic */ View zzciu;
    private final /* synthetic */ HashMap zzciv;
    private final /* synthetic */ HashMap zzciw;

    zzwl(zzvx zzvx, View view, HashMap hashMap, HashMap hashMap2) {
        this.zzcij = zzvx;
        this.zzciu = view;
        this.zzciv = hashMap;
        this.zzciw = hashMap2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.zzciu.getContext(), "native_ad_view_holder_delegate");
        return new zzzv();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcir.zzb(this.zzciu, this.zzciv, this.zzciw);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zza(ObjectWrapper.wrap(this.zzciu), ObjectWrapper.wrap(this.zzciv), ObjectWrapper.wrap(this.zzciw));
    }
}
