package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdap implements Callable {
    private final zzdaq zzguc;
    private final List zzgud;
    private final IObjectWrapper zzgue;

    zzdap(zzdaq zzdaq, List list, IObjectWrapper iObjectWrapper) {
        this.zzguc = zzdaq;
        this.zzgud = list;
        this.zzgue = iObjectWrapper;
    }

    public final Object call() {
        return this.zzguc.zza(this.zzgud, this.zzgue);
    }
}
