package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczk extends zzwx {
    private final zzdaa zzgsy;

    public zzczk(Context context, zzbix zzbix, zzdom zzdom, zzcep zzcep, zzwt zzwt) {
        zzdac zzdac = new zzdac(zzcep);
        zzdac.zzc(zzwt);
        this.zzgsy = new zzdaa(new zzdai(zzbix, context, zzdac, zzdom), zzdom.zzatl());
    }

    public final synchronized boolean isLoading() throws RemoteException {
        return this.zzgsy.isLoading();
    }

    public final void zzb(zzvg zzvg) throws RemoteException {
        this.zzgsy.zza(zzvg, 1);
    }

    public final synchronized void zza(zzvg zzvg, int i) throws RemoteException {
        this.zzgsy.zza(zzvg, i);
    }

    public final synchronized String getMediationAdapterClassName() {
        return this.zzgsy.getMediationAdapterClassName();
    }

    public final synchronized String zzkh() {
        return this.zzgsy.zzkh();
    }
}
