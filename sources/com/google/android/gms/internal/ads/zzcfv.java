package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.WindowManager;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfv implements zzahq {
    private final zzcfu zzgcb;
    private final View zzgcd;
    private final WindowManager zzgce;

    zzcfv(zzcfu zzcfu, View view, WindowManager windowManager) {
        this.zzgcb = zzcfu;
        this.zzgcd = view;
        this.zzgce = windowManager;
    }

    public final void zza(Object obj, Map map) {
        this.zzgcb.zza(this.zzgcd, this.zzgce, (zzbgj) obj, map);
    }
}
