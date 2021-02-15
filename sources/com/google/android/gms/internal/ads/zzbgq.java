package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbgq implements zzdya {
    private final Context zzcyt;
    private final zzeg zzdhk;
    private final zzbbx zzenn;
    private final zzb zzeno;
    private final String zzenp;

    zzbgq(Context context, zzeg zzeg, zzbbx zzbbx, zzb zzb, String str) {
        this.zzcyt = context;
        this.zzdhk = zzeg;
        this.zzenn = zzbbx;
        this.zzeno = zzb;
        this.zzenp = str;
    }

    public final zzdzc zzf(Object obj) {
        Context context = this.zzcyt;
        zzeg zzeg = this.zzdhk;
        zzbbx zzbbx = this.zzenn;
        zzb zzb = this.zzeno;
        String str = this.zzenp;
        zzp.zzks();
        zzbgj zza = zzbgr.zza(context, zzbhy.zzacy(), "", false, false, zzeg, (zzacb) null, zzbbx, (zzabs) null, (zzk) null, zzb, zzts.zzna(), (zzsu) null, false, (zzdnv) null, (zzdnw) null);
        zzbch zzl = zzbch.zzl(zza);
        zza.zzabw().zza((zzbhu) new zzbgs(zzl));
        zza.loadUrl(str);
        return zzl;
    }
}
