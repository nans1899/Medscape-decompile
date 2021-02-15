package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzwi extends zzwn<zzaei> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzvx zzcij;
    private final /* synthetic */ FrameLayout zzcis;
    private final /* synthetic */ FrameLayout zzcit;

    zzwi(zzvx zzvx, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzcij = zzvx;
        this.zzcis = frameLayout;
        this.zzcit = frameLayout2;
        this.val$context = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zzpp() {
        zzvx.zza(this.val$context, "native_ad_view_delegate");
        return new zzzw();
    }

    public final /* synthetic */ Object zzpq() throws RemoteException {
        return this.zzcij.zzcin.zzb(this.val$context, this.zzcis, this.zzcit);
    }

    public final /* synthetic */ Object zza(zzxp zzxp) throws RemoteException {
        return zzxp.zza(ObjectWrapper.wrap(this.zzcis), ObjectWrapper.wrap(this.zzcit));
    }
}
