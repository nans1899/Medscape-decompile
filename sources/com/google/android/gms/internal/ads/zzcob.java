package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcob implements zzdyr<String> {
    final /* synthetic */ zzcnu zzgis;

    zzcob(zzcnu zzcnu) {
        this.zzgis = zzcnu;
    }

    public final void zzb(Throwable th) {
        synchronized (this) {
            boolean unused = this.zzgis.zzgif = true;
            this.zzgis.zza("com.google.android.gms.ads.MobileAds", false, "Internal Error.", (int) (zzp.zzky().elapsedRealtime() - this.zzgis.zzgig));
            this.zzgis.zzgih.setException(new Exception());
        }
    }

    public final /* synthetic */ void onSuccess(@Nullable Object obj) {
        String str = (String) obj;
        synchronized (this) {
            boolean unused = this.zzgis.zzgif = true;
            this.zzgis.zza("com.google.android.gms.ads.MobileAds", true, "", (int) (zzp.zzky().elapsedRealtime() - this.zzgis.zzgig));
            this.zzgis.executor.execute(new zzcoe(this, str));
        }
    }
}
