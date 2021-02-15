package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.internal.measurement.zzct;
import com.google.android.gms.internal.measurement.zzde;
import com.medscape.android.Constants;
import com.tapstream.sdk.http.RequestBuilders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzat {
    public static zzeg<Long> zza = zza("measurement.ad_id_cache_time", 10000L, 10000L, zzas.zza);
    public static zzeg<Long> zzaa = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbn.zza);
    public static zzeg<Integer> zzab = zza("measurement.upload.retry_count", 6, 6, zzbm.zza);
    public static zzeg<Long> zzac = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbp.zza);
    public static zzeg<Integer> zzad = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbo.zza);
    public static zzeg<Integer> zzae = zza("measurement.audience.filter_result_max_count", 200, 200, zzbq.zza);
    public static zzeg<Integer> zzaf = zza("measurement.upload.max_public_user_properties", 25, 25, (zzee) null);
    public static zzeg<Integer> zzag = zza("measurement.upload.max_event_name_cardinality", 500, 500, (zzee) null);
    public static zzeg<Integer> zzah = zza("measurement.upload.max_public_event_params", 25, 25, (zzee) null);
    public static zzeg<Long> zzai;
    public static zzeg<Boolean> zzaj = zza("measurement.test.boolean_flag", false, false, zzbs.zza);
    public static zzeg<String> zzak = zza("measurement.test.string_flag", "---", "---", zzbv.zza);
    public static zzeg<Long> zzal = zza("measurement.test.long_flag", -1L, -1L, zzbu.zza);
    public static zzeg<Integer> zzam = zza("measurement.test.int_flag", -2, -2, zzbx.zza);
    public static zzeg<Double> zzan;
    public static zzeg<Integer> zzao = zza("measurement.experiment.max_ids", 50, 50, zzbz.zza);
    public static zzeg<Integer> zzap = zza("measurement.max_bundles_per_iteration", 100, 100, zzby.zza);
    public static zzeg<Boolean> zzaq = zza("measurement.validation.internal_limits_internal_event_params", false, false, zzcb.zza);
    public static zzeg<Boolean> zzar = zza("measurement.collection.firebase_global_collection_flag_enabled", true, true, zzcd.zza);
    public static zzeg<Boolean> zzas = zza("measurement.collection.efficient_engagement_reporting_enabled_2", true, true, zzcc.zza);
    public static zzeg<Boolean> zzat = zza("measurement.collection.redundant_engagement_removal_enabled", false, false, zzcf.zza);
    public static zzeg<Boolean> zzau = zza("measurement.client.freeride_engagement_fix", true, true, zzce.zza);
    public static zzeg<Boolean> zzav = zza("measurement.collection.log_event_and_bundle_v2", true, true, zzch.zza);
    public static zzeg<Boolean> zzaw = zza("measurement.quality.checksum", false, false, (zzee) null);
    public static zzeg<Boolean> zzax = zza("measurement.sdk.dynamite.allow_remote_dynamite3", false, false, zzcg.zza);
    public static zzeg<Boolean> zzay = zza("measurement.sdk.collection.validate_param_names_alphabetical", true, true, zzcj.zza);
    public static zzeg<Boolean> zzaz = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, false, zzci.zza);
    public static zzeg<Long> zzb;
    public static zzeg<Boolean> zzba = zza("measurement.audience.refresh_event_count_filters_timestamp", false, false, zzcl.zza);
    public static zzeg<Boolean> zzbb = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, false, zzck.zza);
    public static zzeg<Boolean> zzbc = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", true, true, zzcm.zza);
    public static zzeg<Boolean> zzbd = zza("measurement.sdk.collection.last_deep_link_referrer2", true, true, zzcp.zza);
    public static zzeg<Boolean> zzbe = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, false, zzco.zza);
    public static zzeg<Boolean> zzbf = zza("measurement.sdk.collection.last_gclid_from_referrer2", false, false, zzcr.zza);
    public static zzeg<Boolean> zzbg = zza("measurement.sdk.collection.enable_extend_user_property_size", true, true, zzcq.zza);
    public static zzeg<Boolean> zzbh = zza("measurement.upload.file_lock_state_check", false, false, zzct.zza);
    public static zzeg<Boolean> zzbi = zza("measurement.ga.ga_app_id", false, false, zzcs.zza);
    public static zzeg<Boolean> zzbj = zza("measurement.lifecycle.app_in_background_parameter", false, false, zzcv.zza);
    public static zzeg<Boolean> zzbk = zza("measurement.integration.disable_firebase_instance_id", false, false, zzcu.zza);
    public static zzeg<Boolean> zzbl = zza("measurement.lifecycle.app_backgrounded_engagement", false, false, zzcx.zza);
    public static zzeg<Boolean> zzbm = zza("measurement.collection.service.update_with_analytics_fix", false, false, zzcz.zza);
    public static zzeg<Boolean> zzbn = zza("measurement.service.use_appinfo_modified", false, false, zzcy.zza);
    public static zzeg<Boolean> zzbo = zza("measurement.client.firebase_feature_rollout.v1.enable", true, true, zzdb.zza);
    public static zzeg<Boolean> zzbp = zza("measurement.client.sessions.check_on_reset_and_enable2", true, true, zzda.zza);
    public static zzeg<Boolean> zzbq = zza("measurement.scheduler.task_thread.cleanup_on_exit", false, false, zzdd.zza);
    public static zzeg<Boolean> zzbr = zza("measurement.upload.file_truncate_fix", false, false, zzdc.zza);
    public static zzeg<Boolean> zzbs = zza("measurement.sdk.referrer.delayed_install_referrer_api", false, false, zzdf.zza);
    public static zzeg<Boolean> zzbt = zza("measurement.sdk.screen.disabling_automatic_reporting", true, true, zzde.zza);
    public static zzeg<Boolean> zzbu = zza("measurement.sdk.screen.manual_screen_view_logging", true, true, zzdh.zza);
    public static zzeg<Boolean> zzbv = zza("measurement.service.configurable_service_limits", true, true, zzdi.zza);
    public static zzeg<Boolean> zzbw = zza("measurement.client.configurable_service_limits", true, true, zzdl.zza);
    public static zzeg<Boolean> zzbx = zza("measurement.androidId.delete_feature", true, true, zzdk.zza);
    public static zzeg<Boolean> zzby = zza("measurement.client.global_params", true, true, zzdn.zza);
    public static zzeg<Boolean> zzbz = zza("measurement.service.global_params", true, true, zzdm.zza);
    public static zzeg<Long> zzc;
    public static zzeg<Boolean> zzca = zza("measurement.service.global_params_in_payload", true, true, zzdp.zza);
    public static zzeg<Boolean> zzcb = zza("measurement.client.string_reader", true, true, zzdo.zza);
    public static zzeg<Boolean> zzcc = zza("measurement.sdk.attribution.cache", true, true, zzdr.zza);
    public static zzeg<Long> zzcd = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzdq.zza);
    public static zzeg<Boolean> zzce = zza("measurement.service.database_return_empty_collection", true, true, zzdt.zza);
    public static zzeg<Boolean> zzcf = zza("measurement.service.ssaid_removal", true, true, zzdv.zza);
    public static zzeg<Boolean> zzcg = zza("measurement.client.consent_state_v1", true, true, zzdu.zza);
    public static zzeg<Boolean> zzch = zza("measurement.client.3p_consent_state_v1.dev", false, false, zzdx.zza);
    public static zzeg<Boolean> zzci = zza("measurement.service.consent_state_v1_W36", false, false, zzdw.zza);
    public static zzeg<Integer> zzcj = zza("measurement.service.storage_consent_support_version", 203590, 203590, zzdz.zza);
    public static zzeg<Boolean> zzck = zza("measurement.client.ad_impression", true, true, zzdy.zza);
    public static zzeg<Boolean> zzcl = zza("measurement.service.ad_impression", true, true, zzeb.zza);
    public static zzeg<Boolean> zzcm = zza("measurement.service.separate_public_internal_event_blacklisting", true, true, zzea.zza);
    public static zzeg<Boolean> zzcn = zza("measurement.service.directly_maybe_log_error_events", false, false, zzed.zza);
    /* access modifiers changed from: private */
    public static List<zzeg<?>> zzco = Collections.synchronizedList(new ArrayList());
    private static Set<zzeg<?>> zzcp = Collections.synchronizedSet(new HashSet());
    private static zzeg<Boolean> zzcq = zza("measurement.collection.synthetic_data_mitigation", false, false, zzdg.zza);
    public static zzeg<String> zzd = zza("measurement.config.url_scheme", RequestBuilders.DEFAULT_SCHEME, RequestBuilders.DEFAULT_SCHEME, zzbr.zza);
    public static zzeg<String> zze = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzca.zza);
    public static zzeg<Integer> zzf = zza("measurement.upload.max_bundles", 100, 100, zzcn.zza);
    public static zzeg<Integer> zzg = zza("measurement.upload.max_batch_size", 65536, 65536, zzcw.zza);
    public static zzeg<Integer> zzh = zza("measurement.upload.max_bundle_size", 65536, 65536, zzdj.zza);
    public static zzeg<Integer> zzi = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzds.zza);
    public static zzeg<Integer> zzj = zza("measurement.upload.max_events_per_day", 100000, 100000, zzec.zza);
    public static zzeg<Integer> zzk = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzau.zza);
    public static zzeg<Integer> zzl = zza("measurement.upload.max_public_events_per_day", 50000, 50000, zzax.zza);
    public static zzeg<Integer> zzm = zza("measurement.upload.max_conversions_per_day", 10000, 10000, zzaw.zza);
    public static zzeg<Integer> zzn = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzaz.zza);
    public static zzeg<Integer> zzo = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzay.zza);
    public static zzeg<String> zzp = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzbb.zza);
    public static zzeg<Long> zzq = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzba.zza);
    public static zzeg<Long> zzr;
    public static zzeg<Long> zzs;
    public static zzeg<Long> zzt = zza("measurement.upload.realtime_upload_interval", 10000L, 10000L, zzbf.zza);
    public static zzeg<Long> zzu = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzbh.zza);
    public static zzeg<Long> zzv = zza("measurement.upload.minimum_delay", 500L, 500L, zzbg.zza);
    public static zzeg<Long> zzw;
    public static zzeg<Long> zzx;
    public static zzeg<Long> zzy = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbl.zza);
    public static zzeg<Long> zzz = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbk.zza);

    public static Map<String, String> zza(Context context) {
        zzct zza2 = zzct.zza(context.getContentResolver(), zzde.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }

    private static <V> zzeg<V> zza(String str, V v, V v2, zzee<V> zzee) {
        zzeg zzeg = new zzeg(str, v, v2, zzee);
        zzco.add(zzeg);
        return zzeg;
    }

    static {
        Long valueOf = Long.valueOf(Constants.DAY_IN_MILLIS);
        zzb = zza("measurement.monitoring.sample_period_millis", valueOf, valueOf, zzav.zza);
        Long valueOf2 = Long.valueOf(Constants.HOUR_IN_MILLIS);
        zzc = zza("measurement.config.cache_time", valueOf, valueOf2, zzbe.zza);
        zzr = zza("measurement.upload.window_interval", valueOf2, valueOf2, zzbd.zza);
        zzs = zza("measurement.upload.interval", valueOf2, valueOf2, zzbc.zza);
        Long valueOf3 = Long.valueOf(Constants.MINUTE_IN_MILLIS);
        zzw = zza("measurement.alarm_manager.minimum_interval", valueOf3, valueOf3, zzbj.zza);
        zzx = zza("measurement.upload.stale_data_deletion_interval", valueOf, valueOf, zzbi.zza);
        Long valueOf4 = Long.valueOf(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        zzai = zza("measurement.service_client.idle_disconnect_millis", valueOf4, valueOf4, zzbt.zza);
        Double valueOf5 = Double.valueOf(-3.0d);
        zzan = zza("measurement.test.double_flag", valueOf5, valueOf5, zzbw.zza);
    }
}
