package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyr implements zzcyl<zzcbe> {
    private final zzcce zzgos;
    private final Context zzvr;

    public zzcyr(Context context, zzcce zzcce) {
        this.zzvr = context;
        this.zzgos = zzcce;
    }

    public final /* synthetic */ Object zza(zzdog zzdog, zzdnv zzdnv, View view, zzcys zzcys) {
        zzcbg zza = this.zzgos.zza(new zzbre(zzdog, zzdnv, (String) null), new zzcyt(this, zzcyu.zzgsa));
        zzcys.zza(new zzcyw(this, zza));
        return zza.zzagj();
    }
}
