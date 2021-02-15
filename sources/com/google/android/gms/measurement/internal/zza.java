package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zza extends zzd {
    private final Map<String, Long> zza = new ArrayMap();
    private final Map<String, Integer> zzb = new ArrayMap();
    private long zzc;

    public zza(zzfv zzfv) {
        super(zzfv);
    }

    public final void zza(String str, long j) {
        if (str == null || str.length() == 0) {
            zzq().zze().zza("Ad unit id must be a non-empty string");
        } else {
            zzp().zza((Runnable) new zzc(this, str, j));
        }
    }

    /* access modifiers changed from: private */
    public final void zzc(String str, long j) {
        zzc();
        Preconditions.checkNotEmpty(str);
        if (this.zzb.isEmpty()) {
            this.zzc = j;
        }
        Integer num = this.zzb.get(str);
        if (num != null) {
            this.zzb.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.zzb.size() >= 100) {
            zzq().zzh().zza("Too many ads visible");
        } else {
            this.zzb.put(str, 1);
            this.zza.put(str, Long.valueOf(j));
        }
    }

    public final void zzb(String str, long j) {
        if (str == null || str.length() == 0) {
            zzq().zze().zza("Ad unit id must be a non-empty string");
        } else {
            zzp().zza((Runnable) new zzb(this, str, j));
        }
    }

    /* access modifiers changed from: private */
    public final void zzd(String str, long j) {
        zzc();
        Preconditions.checkNotEmpty(str);
        Integer num = this.zzb.get(str);
        if (num != null) {
            zzig zza2 = zzh().zza(false);
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.zzb.remove(str);
                Long l = this.zza.get(str);
                if (l == null) {
                    zzq().zze().zza("First ad unit exposure time was never set");
                } else {
                    this.zza.remove(str);
                    zza(str, j - l.longValue(), zza2);
                }
                if (this.zzb.isEmpty()) {
                    long j2 = this.zzc;
                    if (j2 == 0) {
                        zzq().zze().zza("First ad exposure time was never set");
                        return;
                    }
                    zza(j - j2, zza2);
                    this.zzc = 0;
                    return;
                }
                return;
            }
            this.zzb.put(str, Integer.valueOf(intValue));
            return;
        }
        zzq().zze().zza("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    private final void zza(long j, zzig zzig) {
        if (zzig == null) {
            zzq().zzw().zza("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            zzq().zzw().zza("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzij.zza(zzig, bundle, true);
            zze().zza("am", "_xa", bundle);
        }
    }

    private final void zza(String str, long j, zzig zzig) {
        if (zzig == null) {
            zzq().zzw().zza("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            zzq().zzw().zza("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzij.zza(zzig, bundle, true);
            zze().zza("am", "_xu", bundle);
        }
    }

    public final void zza(long j) {
        zzig zza2 = zzh().zza(false);
        for (String next : this.zza.keySet()) {
            zza(next, j - this.zza.get(next).longValue(), zza2);
        }
        if (!this.zza.isEmpty()) {
            zza(j - this.zzc, zza2);
        }
        zzb(j);
    }

    /* access modifiers changed from: private */
    public final void zzb(long j) {
        for (String put : this.zza.keySet()) {
            this.zza.put(put, Long.valueOf(j));
        }
        if (!this.zza.isEmpty()) {
            this.zzc = j;
        }
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
