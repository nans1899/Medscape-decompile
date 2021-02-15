package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzciy implements Callable<zzcil> {
    /* access modifiers changed from: private */
    public final zzbgr zzbpt;
    /* access modifiers changed from: private */
    public final zzbbx zzdqm;
    /* access modifiers changed from: private */
    public final zzeg zzeom;
    /* access modifiers changed from: private */
    public final Executor zzfoc;
    /* access modifiers changed from: private */
    public final zzb zzgdm;
    /* access modifiers changed from: private */
    public final Context zzvr;

    public zzciy(Context context, Executor executor, zzeg zzeg, zzbbx zzbbx, zzb zzb, zzbgr zzbgr) {
        this.zzvr = context;
        this.zzfoc = executor;
        this.zzeom = zzeg;
        this.zzdqm = zzbbx;
        this.zzgdm = zzb;
        this.zzbpt = zzbgr;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzcil zzcil = new zzcil(this);
        zzcil.zzans();
        return zzcil;
    }
}
