package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcv implements zzdgx<Object> {
    private static final Object lock = new Object();
    private final String zzcja;
    private final String zzdqu;
    private final zzdok zzfsk;
    private final zzbsa zzgvu;
    private final zzdpj zzgvv;

    public zzdcv(String str, String str2, zzbsa zzbsa, zzdpj zzdpj, zzdok zzdok) {
        this.zzdqu = str;
        this.zzcja = str2;
        this.zzgvu = zzbsa;
        this.zzgvv = zzdpj;
        this.zzfsk = zzdok;
    }

    public final zzdzc<Object> zzarj() {
        Bundle bundle = new Bundle();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcve)).booleanValue()) {
            this.zzgvu.zzf(this.zzfsk.zzhfa);
            bundle.putAll(this.zzgvv.zzatu());
        }
        return zzdyq.zzaf(new zzdcu(this, bundle));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Bundle bundle, Bundle bundle2) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcve)).booleanValue()) {
            bundle2.putBundle("quality_signals", bundle);
        } else {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvd)).booleanValue()) {
                synchronized (lock) {
                    this.zzgvu.zzf(this.zzfsk.zzhfa);
                    bundle2.putBundle("quality_signals", this.zzgvv.zzatu());
                }
            } else {
                this.zzgvu.zzf(this.zzfsk.zzhfa);
                bundle2.putBundle("quality_signals", this.zzgvv.zzatu());
            }
        }
        bundle2.putString("seq_num", this.zzdqu);
        bundle2.putString("session_id", this.zzcja);
    }
}
