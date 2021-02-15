package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzbja;
import com.google.android.gms.internal.ads.zzbky;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzbix implements zzbly {
    private static zzbix zzesl;

    /* access modifiers changed from: protected */
    public abstract zzdhp zza(zzdiy zzdiy);

    public abstract Executor zzadi();

    public abstract ScheduledExecutorService zzadj();

    public abstract Executor zzadk();

    public abstract zzdzb zzadl();

    public abstract zzbwk zzadm();

    public abstract zzcku zzadn();

    public abstract zzbld zzado();

    public abstract zzbpl zzadp();

    public abstract zzbnp zzadq();

    public abstract zzboe zzadr();

    public abstract zzdjx zzads();

    public abstract zzccd zzadt();

    public abstract zzccz zzadu();

    public abstract zzcjm zzadv();

    public abstract zzdnm zzadw();

    public abstract zzdan zzadx();

    public abstract zzdaq zzady();

    public abstract zzcsy zzadz();

    public abstract zzdoz<zzcil> zzaea();

    public static zzbix zza(Context context, zzanb zzanb, int i) {
        zzbix zzf = zzf(context, i);
        zzf.zzadn().zzb(zzanb);
        return zzf;
    }

    @Deprecated
    public static zzbix zzf(Context context, int i) {
        synchronized (zzbix.class) {
            if (zzesl == null) {
                return zza(new zzbbx(202510000, i, true, false), context, (zzbky.zza) new zzbjs());
            }
            zzbix zzbix = zzesl;
            return zzbix;
        }
    }

    @Deprecated
    private static zzbix zza(zzbbx zzbbx, Context context, zzbky.zza zza) {
        zzbix zzbix;
        synchronized (zzbix.class) {
            if (zzesl == null) {
                zzesl = new zzbkm().zzc(new zzbja(new zzbja.zza().zza(zzbbx).zzbx(context))).zza(new zzbky(zza)).zzagf();
                zzabb.initialize(context);
                zzp.zzkv().zzd(context, zzbbx);
                zzp.zzkx().initialize(context);
                zzp.zzkr().zzap(context);
                zzp.zzkr().zzaq(context);
                zzayn.zzao(context);
                zzp.zzku().initialize(context);
                zzp.zzlm().initialize(context);
                if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue()) {
                    new zzcsm(context, zzbbx, new zzts(new zztx(context)), new zzcrw(new zzcru(context), zzesl.zzadl())).zzaqh();
                }
            }
            zzbix = zzesl;
        }
        return zzbix;
    }

    public final zzdhp zza(zzatc zzatc, int i) {
        return zza(new zzdiy(zzatc, i));
    }
}
