package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.WindowManager;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfw implements zzahq {
    private final zzcfu zzgcb;
    private final WindowManager zzgcf;
    private final View zzgcg;

    zzcfw(zzcfu zzcfu, WindowManager windowManager, View view) {
        this.zzgcb = zzcfu;
        this.zzgcf = windowManager;
        this.zzgcg = view;
    }

    public final void zza(Object obj, Map map) {
        this.zzgcb.zza(this.zzgcf, this.zzgcg, (zzbgj) obj, map);
    }
}
