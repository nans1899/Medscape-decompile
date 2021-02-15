package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.medscape.android.Constants;
import com.tapstream.sdk.http.RequestBuilders;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzmf implements zzmg {
    private static final zzdh<Long> zza;
    private static final zzdh<Long> zzaa;
    private static final zzdh<Long> zzab;
    private static final zzdh<Long> zzac;
    private static final zzdh<Long> zzad;
    private static final zzdh<Long> zzae;
    private static final zzdh<Long> zzaf;
    private static final zzdh<Long> zzag;
    private static final zzdh<Long> zzah;
    private static final zzdh<String> zzai;
    private static final zzdh<Long> zzaj;
    private static final zzdh<Long> zzb;
    private static final zzdh<String> zzc;
    private static final zzdh<String> zzd;
    private static final zzdh<String> zze;
    private static final zzdh<Long> zzf;
    private static final zzdh<Long> zzg;
    private static final zzdh<Long> zzh;
    private static final zzdh<Long> zzi;
    private static final zzdh<Long> zzj;
    private static final zzdh<Long> zzk;
    private static final zzdh<Long> zzl;
    private static final zzdh<Long> zzm;
    private static final zzdh<Long> zzn;
    private static final zzdh<Long> zzo;
    private static final zzdh<Long> zzp;
    private static final zzdh<Long> zzq;
    private static final zzdh<String> zzr;
    private static final zzdh<Long> zzs;
    private static final zzdh<Long> zzt;
    private static final zzdh<Long> zzu;
    private static final zzdh<Long> zzv;
    private static final zzdh<Long> zzw;
    private static final zzdh<Long> zzx;
    private static final zzdh<Long> zzy;
    private static final zzdh<Long> zzz;

    public final long zza() {
        return zza.zzc().longValue();
    }

    public final long zzb() {
        return zzb.zzc().longValue();
    }

    public final String zzc() {
        return zzd.zzc();
    }

    public final String zzd() {
        return zze.zzc();
    }

    public final long zze() {
        return zzf.zzc().longValue();
    }

    public final long zzf() {
        return zzg.zzc().longValue();
    }

    public final long zzg() {
        return zzh.zzc().longValue();
    }

    public final long zzh() {
        return zzi.zzc().longValue();
    }

    public final long zzi() {
        return zzj.zzc().longValue();
    }

    public final long zzj() {
        return zzk.zzc().longValue();
    }

    public final long zzk() {
        return zzl.zzc().longValue();
    }

    public final long zzl() {
        return zzm.zzc().longValue();
    }

    public final long zzm() {
        return zzn.zzc().longValue();
    }

    public final long zzn() {
        return zzo.zzc().longValue();
    }

    public final long zzo() {
        return zzq.zzc().longValue();
    }

    public final long zzp() {
        return zzs.zzc().longValue();
    }

    public final long zzq() {
        return zzt.zzc().longValue();
    }

    public final long zzr() {
        return zzu.zzc().longValue();
    }

    public final long zzs() {
        return zzv.zzc().longValue();
    }

    public final long zzt() {
        return zzw.zzc().longValue();
    }

    public final long zzu() {
        return zzx.zzc().longValue();
    }

    public final long zzv() {
        return zzy.zzc().longValue();
    }

    public final long zzw() {
        return zzz.zzc().longValue();
    }

    public final long zzx() {
        return zzaa.zzc().longValue();
    }

    public final long zzy() {
        return zzab.zzc().longValue();
    }

    public final long zzz() {
        return zzac.zzc().longValue();
    }

    public final long zzaa() {
        return zzad.zzc().longValue();
    }

    public final long zzab() {
        return zzae.zzc().longValue();
    }

    public final long zzac() {
        return zzaf.zzc().longValue();
    }

    public final long zzad() {
        return zzag.zzc().longValue();
    }

    public final long zzae() {
        return zzah.zzc().longValue();
    }

    public final String zzaf() {
        return zzai.zzc();
    }

    public final long zzag() {
        return zzaj.zzc().longValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.ad_id_cache_time", 10000);
        zzb = zzdm.zza("measurement.config.cache_time", (long) Constants.DAY_IN_MILLIS);
        zzc = zzdm.zza("measurement.log_tag", "FA");
        zzd = zzdm.zza("measurement.config.url_authority", "app-measurement.com");
        zze = zzdm.zza("measurement.config.url_scheme", RequestBuilders.DEFAULT_SCHEME);
        zzf = zzdm.zza("measurement.upload.debug_upload_interval", 1000);
        zzg = zzdm.zza("measurement.lifetimevalue.max_currency_tracked", 4);
        zzh = zzdm.zza("measurement.store.max_stored_events_per_app", 100000);
        zzi = zzdm.zza("measurement.experiment.max_ids", 50);
        zzj = zzdm.zza("measurement.audience.filter_result_max_count", 200);
        zzk = zzdm.zza("measurement.alarm_manager.minimum_interval", (long) Constants.MINUTE_IN_MILLIS);
        zzl = zzdm.zza("measurement.upload.minimum_delay", 500);
        zzm = zzdm.zza("measurement.monitoring.sample_period_millis", (long) Constants.DAY_IN_MILLIS);
        zzn = zzdm.zza("measurement.upload.realtime_upload_interval", 10000);
        zzo = zzdm.zza("measurement.upload.refresh_blacklisted_config_interval", 604800000);
        zzp = zzdm.zza("measurement.config.cache_time.service", (long) Constants.HOUR_IN_MILLIS);
        zzq = zzdm.zza("measurement.service_client.idle_disconnect_millis", (long) CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzr = zzdm.zza("measurement.log_tag.service", "FA-SVC");
        zzs = zzdm.zza("measurement.upload.stale_data_deletion_interval", (long) Constants.DAY_IN_MILLIS);
        zzt = zzdm.zza("measurement.upload.backoff_period", 43200000);
        zzu = zzdm.zza("measurement.upload.initial_upload_delay_time", 15000);
        zzv = zzdm.zza("measurement.upload.interval", (long) Constants.HOUR_IN_MILLIS);
        zzw = zzdm.zza("measurement.upload.max_bundle_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzx = zzdm.zza("measurement.upload.max_bundles", 100);
        zzy = zzdm.zza("measurement.upload.max_conversions_per_day", 500);
        zzz = zzdm.zza("measurement.upload.max_error_events_per_day", 1000);
        zzaa = zzdm.zza("measurement.upload.max_events_per_bundle", 1000);
        zzab = zzdm.zza("measurement.upload.max_events_per_day", 100000);
        zzac = zzdm.zza("measurement.upload.max_public_events_per_day", 50000);
        zzad = zzdm.zza("measurement.upload.max_queue_time", 2419200000L);
        zzae = zzdm.zza("measurement.upload.max_realtime_events_per_day", 10);
        zzaf = zzdm.zza("measurement.upload.max_batch_size", (long) PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzag = zzdm.zza("measurement.upload.retry_count", 6);
        zzah = zzdm.zza("measurement.upload.retry_time", 1800000);
        zzai = zzdm.zza("measurement.upload.url", "https://app-measurement.com/a");
        zzaj = zzdm.zza("measurement.upload.window_interval", (long) Constants.HOUR_IN_MILLIS);
    }
}
