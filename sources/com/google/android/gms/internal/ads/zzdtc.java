package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.internal.ads.zzbw;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.medscape.android.Constants;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdtc {
    private static volatile zzbw.zza.zzc zzhme = zzbw.zza.zzc.UNKNOWN;
    private final Executor executor;
    private final Task<zztx> zzhmd;
    private final Context zzvr;

    static void zzb(zzbw.zza.zzc zzc) {
        zzhme = zzc;
    }

    public static zzdtc zza(Context context, Executor executor2) {
        return new zzdtc(context, executor2, Tasks.call(executor2, new zzdtb(context)));
    }

    private zzdtc(Context context, Executor executor2, Task<zztx> task) {
        this.zzvr = context;
        this.executor = executor2;
        this.zzhmd = task;
    }

    public final Task<Boolean> zzg(int i, long j) {
        return zza(i, j, (Exception) null, (String) null, (Map<String, String>) null, (String) null);
    }

    public final Task<Boolean> zza(int i, long j, Exception exc) {
        return zza(i, j, exc, (String) null, (Map<String, String>) null, (String) null);
    }

    public final Task<Boolean> zza(int i, long j, String str, Map<String, String> map) {
        return zza(i, j, (Exception) null, str, (Map<String, String>) null, (String) null);
    }

    public final Task<Boolean> zzg(int i, String str) {
        return zza(Constants.REQUEST_CODE_PICTURE_CHOOSE_SECOND, 0, (Exception) null, (String) null, (Map<String, String>) null, str);
    }

    private final Task<Boolean> zza(int i, long j, Exception exc, String str, Map<String, String> map, String str2) {
        zzbw.zza.C0006zza zzc = zzbw.zza.zzs().zzk(this.zzvr.getPackageName()).zzc(j);
        zzc.zza(zzhme);
        if (exc != null) {
            zzc.zzl(zzdwh.zza(exc)).zzm(exc.getClass().getName());
        }
        if (str2 != null) {
            zzc.zzn(str2);
        }
        if (str != null) {
            zzc.zzo(str);
        }
        return this.zzhmd.continueWith(this.executor, new zzdte(zzc, i));
    }

    static final /* synthetic */ Boolean zza(zzbw.zza.C0006zza zza, int i, Task task) throws Exception {
        if (!task.isSuccessful()) {
            return false;
        }
        zzub zzf = ((zztx) task.getResult()).zzf(((zzbw.zza) ((zzejz) zza.zzbgt())).toByteArray());
        zzf.zzby(i);
        zzf.zzdx();
        return true;
    }

    static final /* synthetic */ zztx zzcj(Context context) throws Exception {
        return new zztx(context, "GLAS", (String) null);
    }
}
