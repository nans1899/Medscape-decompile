package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzq;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzju extends zzg {
    protected final zzkc zza = new zzkc(this);
    protected final zzka zzb = new zzka(this);
    /* access modifiers changed from: private */
    public Handler zzc;
    private final zzjz zzd = new zzjz(this);

    zzju(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final boolean zzy() {
        return false;
    }

    /* access modifiers changed from: private */
    public final void zzaa() {
        zzc();
        if (this.zzc == null) {
            this.zzc = new zzq(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: private */
    public final void zzb(long j) {
        zzc();
        zzaa();
        zzq().zzw().zza("Activity resumed, time", Long.valueOf(j));
        if (zzs().zza(zzat.zzbu)) {
            if (zzs().zzh().booleanValue() || zzr().zzr.zza()) {
                this.zzb.zza(j);
            }
            this.zzd.zza();
        } else {
            this.zzd.zza();
            if (zzs().zzh().booleanValue()) {
                this.zzb.zza(j);
            }
        }
        zzkc zzkc = this.zza;
        zzkc.zza.zzc();
        if (zzkc.zza.zzy.zzaa()) {
            if (!zzkc.zza.zzs().zza(zzat.zzbu)) {
                zzkc.zza.zzr().zzr.zza(false);
            }
            zzkc.zza(zzkc.zza.zzl().currentTimeMillis(), false);
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(long j) {
        zzc();
        zzaa();
        zzq().zzw().zza("Activity paused, time", Long.valueOf(j));
        this.zzd.zza(j);
        if (zzs().zzh().booleanValue()) {
            this.zzb.zzb(j);
        }
        zzkc zzkc = this.zza;
        if (!zzkc.zza.zzs().zza(zzat.zzbu)) {
            zzkc.zza.zzr().zzr.zza(true);
        }
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        return this.zzb.zza(z, z2, j);
    }

    /* access modifiers changed from: package-private */
    public final long zza(long j) {
        return this.zzb.zzc(j);
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzgy zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzek zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzio zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzij zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzen zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzju zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzep zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkw zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzfo zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzer zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfd zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }
}
