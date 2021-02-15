package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzcxa extends zzcyc {
    private zzcal zzgqk;

    public zzcxa(zzbuc zzbuc, zzbuu zzbuu, zzbvh zzbvh, zzbvr zzbvr, zzbuk zzbuk, zzbyf zzbyf, zzcau zzcau, zzbwa zzbwa, zzcal zzcal, zzbxy zzbxy) {
        super(zzbuc, zzbuu, zzbvh, zzbvr, zzbyf, zzbwa, zzcau, zzbxy, zzbuk);
        this.zzgqk = zzcal;
    }

    public final void zzuc() {
        this.zzgqk.zzta();
    }

    public final void zzud() throws RemoteException {
        this.zzgqk.zztb();
    }

    public final void zza(zzaux zzaux) throws RemoteException {
        super.zza(zzaux);
        this.zzgqk.zza(new zzauv(zzaux.getType(), zzaux.getAmount()));
    }

    public final void zzb(zzauv zzauv) {
        super.zzb(zzauv);
        this.zzgqk.zza(zzauv);
    }

    public final void onVideoEnd() {
        this.zzgqk.zztb();
    }
}
