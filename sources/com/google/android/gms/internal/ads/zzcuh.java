package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcuh extends zzaoy {
    private zzctc<zzaph, zzcuu> zzgoq;
    private final /* synthetic */ zzcug zzgor;

    private zzcuh(zzcug zzcug, zzctc<zzaph, zzcuu> zzctc) {
        this.zzgor = zzcug;
        this.zzgoq = zzctc;
    }

    public final void zzx(IObjectWrapper iObjectWrapper) throws RemoteException {
        View unused = this.zzgor.view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        ((zzcuu) this.zzgoq.zzgns).onAdLoaded();
    }

    public final void zzdq(String str) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(0, str);
    }

    public final void zzf(zzva zzva) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(zzva);
    }
}
