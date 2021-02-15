package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcvk extends zzaoz {
    private zzctc<zzaph, zzcuu> zzgoq;

    private zzcvk(zzcvi zzcvi, zzctc<zzaph, zzcuu> zzctc) {
        this.zzgoq = zzctc;
    }

    public final void zzul() throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).onAdLoaded();
    }

    public final void zzdq(String str) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(0, str);
    }

    public final void zzf(zzva zzva) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(zzva);
    }
}
