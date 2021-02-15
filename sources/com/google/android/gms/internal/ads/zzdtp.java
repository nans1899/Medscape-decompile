package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.internal.ads.zzcf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdtp {
    private final Executor executor;
    private final zzdtv zzhmq;
    private final zzdtv zzhmr;
    private Task<zzcf.zza> zzhms;
    private Task<zzcf.zza> zzhmt;
    private final Context zzvr;
    private final zzdtc zzvw;
    private final zzdtd zzyr;

    private zzdtp(Context context, Executor executor2, zzdtc zzdtc, zzdtd zzdtd, zzdtt zzdtt, zzdtw zzdtw) {
        this.zzvr = context;
        this.executor = executor2;
        this.zzvw = zzdtc;
        this.zzyr = zzdtd;
        this.zzhmq = zzdtt;
        this.zzhmr = zzdtw;
    }

    public static zzdtp zza(Context context, Executor executor2, zzdtc zzdtc, zzdtd zzdtd) {
        zzdtp zzdtp = new zzdtp(context, executor2, zzdtc, zzdtd, new zzdtt(), new zzdtw());
        if (zzdtp.zzyr.zzavu()) {
            zzdtp.zzhms = zzdtp.zzd(new zzdts(zzdtp));
        } else {
            zzdtp.zzhms = Tasks.forResult(zzdtp.zzhmq.zzawe());
        }
        zzdtp.zzhmt = zzdtp.zzd(new zzdtr(zzdtp));
        return zzdtp;
    }

    public final zzcf.zza zzawa() {
        return zza(this.zzhms, this.zzhmq.zzawe());
    }

    public final zzcf.zza zzco() {
        return zza(this.zzhmt, this.zzhmr.zzawe());
    }

    private final Task<zzcf.zza> zzd(Callable<zzcf.zza> callable) {
        return Tasks.call(this.executor, callable).addOnFailureListener(this.executor, (OnFailureListener) new zzdtu(this));
    }

    private static zzcf.zza zza(Task<zzcf.zza> task, zzcf.zza zza) {
        if (!task.isSuccessful()) {
            return zza;
        }
        return task.getResult();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Exception exc) {
        if (exc instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        this.zzvw.zza(2025, -1, exc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzcf.zza zzawb() throws Exception {
        return this.zzhmr.zzck(this.zzvr);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzcf.zza zzawc() throws Exception {
        return this.zzhmq.zzck(this.zzvr);
    }
}
