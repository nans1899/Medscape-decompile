package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcvx extends zzape {
    private zzctc<zzaph, zzcuu> zzgoq;
    private final /* synthetic */ zzcvv zzgps;

    private zzcvx(zzcvv zzcvv, zzctc<zzaph, zzcuu> zzctc) {
        this.zzgps = zzcvv;
        this.zzgoq = zzctc;
    }

    public final void zza(zzanu zzanu) throws RemoteException {
        zzanu unused = this.zzgps.zzgpr = zzanu;
        ((zzcuu) this.zzgoq.zzgns).onAdLoaded();
    }

    public final void zzdq(String str) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(0, str);
    }

    public final void zzf(zzva zzva) throws RemoteException {
        ((zzcuu) this.zzgoq.zzgns).zzc(zzva);
    }
}
