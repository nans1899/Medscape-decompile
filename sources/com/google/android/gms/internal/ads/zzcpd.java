package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzcpd implements zzdya<zzatc, zzdog> {
    /* access modifiers changed from: private */
    public final zzbwr zzgjr;

    public zzcpd(zzbwr zzbwr) {
        this.zzgjr = zzbwr;
    }

    /* access modifiers changed from: protected */
    public abstract zzdzc<zzdog> zzb(zzatc zzatc) throws zzcof;

    public final /* synthetic */ zzdzc zzf(Object obj) throws Exception {
        zzatc zzatc = (zzatc) obj;
        this.zzgjr.zzd(zzatc);
        zzdzc<zzdog> zzb = zzb(zzatc);
        zzdyq.zza(zzb, new zzcpg(this), zzbbz.zzeeu);
        return zzb;
    }
}
