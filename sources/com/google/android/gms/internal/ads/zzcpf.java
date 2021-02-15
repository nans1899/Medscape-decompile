package com.google.android.gms.internal.ads;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpf extends zzcpd {
    /* access modifiers changed from: private */
    public static final Pattern zzgju = Pattern.compile("Received error HTTP response code: (.*)");
    private final ScheduledExecutorService zzfmv;
    private final zzdok zzfsk;
    private final zzdzb zzgcz;
    private final zzcoi zzgjs;
    /* access modifiers changed from: private */
    public final zzcsc zzgjt;

    zzcpf(zzbwr zzbwr, zzdok zzdok, zzcoi zzcoi, zzdzb zzdzb, ScheduledExecutorService scheduledExecutorService, zzcsc zzcsc) {
        super(zzbwr);
        this.zzfsk = zzdok;
        this.zzgjs = zzcoi;
        this.zzgcz = zzdzb;
        this.zzfmv = scheduledExecutorService;
        this.zzgjt = zzcsc;
    }

    public final zzdzc<zzdog> zzb(zzatc zzatc) throws zzcof {
        zzdzc<zzdog> zzb = zzdyq.zzb(this.zzgjs.zze(zzatc), new zzcpi(this), (Executor) this.zzgcz);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcva)).booleanValue()) {
            zzb = zzdyq.zzb(zzdyq.zza(zzb, (long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcvb)).intValue(), TimeUnit.SECONDS, this.zzfmv), TimeoutException.class, zzcph.zzboo, zzbbz.zzeeu);
        }
        zzdyq.zza(zzb, new zzcpk(this), zzbbz.zzeeu);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzd(InputStream inputStream) throws Exception {
        return zzdyq.zzaf(new zzdog(new zzdof(this.zzfsk), zzdoe.zza(new InputStreamReader(inputStream))));
    }
}
