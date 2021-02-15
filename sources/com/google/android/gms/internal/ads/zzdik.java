package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdik implements zzdgx<zzdil> {
    private String packageName;
    private zzdzb zzgcz;
    private zzaxt zzgzh;

    public zzdik(zzaxt zzaxt, zzdzb zzdzb, String str) {
        this.zzgzh = zzaxt;
        this.zzgcz = zzdzb;
        this.packageName = str;
    }

    public final zzdzc<zzdil> zzarj() {
        new zzbcg();
        zzdzc<String> zzaf = zzdyq.zzaf(null);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvp)).booleanValue()) {
            zzaf = this.zzgzh.zzeg(this.packageName);
        }
        zzdzc<String> zzeh = this.zzgzh.zzeh(this.packageName);
        return zzdyq.zzb((zzdzc<? extends V>[]) new zzdzc[]{zzaf, zzeh}).zza(new zzdin(zzaf, zzeh), zzbbz.zzeep);
    }
}
