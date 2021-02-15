package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzkh extends zzaj {
    private final /* synthetic */ zzki zza;
    private final /* synthetic */ zzke zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkh(zzke zzke, zzgq zzgq, zzki zzki) {
        super(zzgq);
        this.zzb = zzke;
        this.zza = zzki;
    }

    public final void zza() {
        this.zzb.zze();
        this.zzb.zzq().zzw().zza("Starting upload from DelayedRunnable");
        this.zza.zzo();
    }
}
