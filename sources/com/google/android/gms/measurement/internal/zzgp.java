package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgp implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzfw zze;

    zzgp(zzfw zzfw, String str, String str2, String str3, long j) {
        this.zze = zzfw;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zzu().zzu().zza(this.zzb, (zzig) null);
            return;
        }
        this.zze.zza.zzu().zzu().zza(this.zzb, new zzig(this.zzc, str, this.zzd));
    }
}
