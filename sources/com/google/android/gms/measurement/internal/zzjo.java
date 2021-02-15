package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzmj;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzjo extends zzkj {
    private String zzb;
    private boolean zzc;
    private long zzd;

    zzjo(zzki zzki) {
        super(zzki);
    }

    /* access modifiers changed from: protected */
    public final boolean zzd() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str, zzad zzad) {
        if (!zzmj.zzb() || !zzs().zza(zzat.zzci) || zzad.zzc()) {
            return zzb(str);
        }
        return new Pair<>("", false);
    }

    @Deprecated
    private final Pair<String, Boolean> zzb(String str) {
        zzc();
        long elapsedRealtime = zzl().elapsedRealtime();
        if (this.zzb != null && elapsedRealtime < this.zzd) {
            return new Pair<>(this.zzb, Boolean.valueOf(this.zzc));
        }
        this.zzd = elapsedRealtime + zzs().zze(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzm());
            if (advertisingIdInfo != null) {
                this.zzb = advertisingIdInfo.getId();
                this.zzc = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzb == null) {
                this.zzb = "";
            }
        } catch (Exception e) {
            zzq().zzv().zza("Unable to get advertising id", e);
            this.zzb = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzb, Boolean.valueOf(this.zzc));
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public final String zza(String str) {
        zzc();
        String str2 = (String) zzb(str).first;
        MessageDigest zzh = zzkw.zzh();
        if (zzh == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzh.digest(str2.getBytes()))});
    }

    public final /* bridge */ /* synthetic */ zzjo zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzks f_() {
        return super.f_();
    }

    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzfp zzj() {
        return super.zzj();
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
