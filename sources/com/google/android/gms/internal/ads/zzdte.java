package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbw;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final /* synthetic */ class zzdte implements Continuation {
    private final int zzebs;
    private final zzbw.zza.C0006zza zzhmf;

    zzdte(zzbw.zza.C0006zza zza, int i) {
        this.zzhmf = zza;
        this.zzebs = i;
    }

    public final Object then(Task task) {
        return zzdtc.zza(this.zzhmf, this.zzebs, task);
    }
}
