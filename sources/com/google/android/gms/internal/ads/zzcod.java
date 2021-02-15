package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcod extends zzair {
    private final /* synthetic */ zzcnu zzgis;
    private final /* synthetic */ Object zzgit;
    private final /* synthetic */ String zzgiu;
    private final /* synthetic */ long zzgiv;
    private final /* synthetic */ zzbcg zzgiw;

    zzcod(zzcnu zzcnu, Object obj, String str, long j, zzbcg zzbcg) {
        this.zzgis = zzcnu;
        this.zzgit = obj;
        this.zzgiu = str;
        this.zzgiv = j;
        this.zzgiw = zzbcg;
    }

    public final void onInitializationSucceeded() {
        synchronized (this.zzgit) {
            this.zzgis.zza(this.zzgiu, true, "", (int) (zzp.zzky().elapsedRealtime() - this.zzgiv));
            this.zzgis.zzgik.zzgk(this.zzgiu);
            this.zzgiw.set(true);
        }
    }

    public final void onInitializationFailed(String str) {
        synchronized (this.zzgit) {
            this.zzgis.zza(this.zzgiu, false, str, (int) (zzp.zzky().elapsedRealtime() - this.zzgiv));
            this.zzgis.zzgik.zzs(this.zzgiu, "error");
            this.zzgiw.set(false);
        }
    }
}
