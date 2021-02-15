package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.measurement.zzmj;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfd extends zzgr {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private String zzaa;
    private boolean zzab;
    private long zzac;
    public zzfg zzb;
    public final zzfh zzc = new zzfh(this, "last_upload", 0);
    public final zzfh zzd = new zzfh(this, "last_upload_attempt", 0);
    public final zzfh zze = new zzfh(this, "backoff", 0);
    public final zzfh zzf = new zzfh(this, "last_delete_stale", 0);
    public final zzfh zzg = new zzfh(this, "midnight_offset", 0);
    public final zzfh zzh = new zzfh(this, "first_open_time", 0);
    public final zzfh zzi = new zzfh(this, "app_install_time", 0);
    public final zzfj zzj = new zzfj(this, "app_instance_id", (String) null);
    public final zzfh zzk = new zzfh(this, "time_before_start", 10000);
    public final zzfh zzl = new zzfh(this, "session_timeout", 1800000);
    public final zzff zzm = new zzff(this, "start_new_session", true);
    public final zzfj zzn = new zzfj(this, "non_personalized_ads", (String) null);
    public final zzff zzo = new zzff(this, "allow_remote_dynamite", false);
    public final zzfh zzp = new zzfh(this, "last_pause_time", 0);
    public boolean zzq;
    public zzff zzr = new zzff(this, "app_backgrounded", false);
    public zzff zzs = new zzff(this, "deep_link_retrieval_complete", false);
    public zzfh zzt = new zzfh(this, "deep_link_retrieval_attempts", 0);
    public final zzfj zzu = new zzfj(this, "firebase_feature_rollouts", (String) null);
    public final zzfj zzv = new zzfj(this, "deferred_attribution_cache", (String) null);
    public final zzfh zzw = new zzfh(this, "deferred_attribution_cache_timestamp", 0);
    public final zzfe zzx = new zzfe(this, "default_event_parameters", (Bundle) null);
    private SharedPreferences zzz;

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str) {
        zzc();
        long elapsedRealtime = zzl().elapsedRealtime();
        if (this.zzaa != null && elapsedRealtime < this.zzac) {
            return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
        }
        this.zzac = elapsedRealtime + zzs().zze(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzm());
            if (advertisingIdInfo != null) {
                this.zzaa = advertisingIdInfo.getId();
                this.zzab = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzaa == null) {
                this.zzaa = "";
            }
        } catch (Exception e) {
            zzq().zzv().zza("Unable to get advertising id", e);
            this.zzaa = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzaa, Boolean.valueOf(this.zzab));
    }

    /* access modifiers changed from: protected */
    public final boolean zzd() {
        return true;
    }

    zzfd(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final void g_() {
        SharedPreferences sharedPreferences = zzm().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzz = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzq = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzz.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzfg(this, "health_monitor", Math.max(0, zzat.zzb.zza(null).longValue()));
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zzf() {
        zzc();
        zzaa();
        return this.zzz;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str) {
        zzc();
        SharedPreferences.Editor edit = zzf().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final String zzg() {
        zzc();
        return zzf().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzc();
        SharedPreferences.Editor edit = zzf().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final String zzh() {
        zzc();
        return zzf().getString("admob_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzi() {
        zzc();
        if (!zzf().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzf().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzc();
        SharedPreferences.Editor edit = zzf().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzj() {
        zzc();
        Boolean zzu2 = zzu();
        SharedPreferences.Editor edit = zzf().edit();
        edit.clear();
        edit.apply();
        if (zzu2 != null) {
            zza(zzu2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(Boolean bool) {
        zzc();
        SharedPreferences.Editor edit = zzf().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzu() {
        zzc();
        if (zzf().contains("measurement_enabled")) {
            return Boolean.valueOf(zzf().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Boolean bool) {
        if (zzmj.zzb() && zzs().zza(zzat.zzcg)) {
            zzc();
            SharedPreferences.Editor edit = zzf().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzv() {
        if (!zzmj.zzb() || !zzs().zza(zzat.zzcg)) {
            return null;
        }
        zzc();
        if (zzf().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(zzf().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzad zzad, int i) {
        if (!zzmj.zzb() || !zzs().zza(zzat.zzcg)) {
            return false;
        }
        zzc();
        if (!zza(i)) {
            return false;
        }
        SharedPreferences.Editor edit = zzf().edit();
        edit.putString("consent_settings", zzad.zza());
        edit.putInt("consent_source", i);
        edit.apply();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(int i) {
        return zzad.zza(i, zzf().getInt("consent_source", 100));
    }

    /* access modifiers changed from: package-private */
    public final zzad zzw() {
        zzc();
        return zzad.zza(zzf().getString("consent_settings", "G1"));
    }

    /* access modifiers changed from: protected */
    public final String zzx() {
        zzc();
        String string = zzf().getString("previous_os_version", (String) null);
        zzk().zzaa();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzf().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        zzc();
        zzq().zzw().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzf().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzy() {
        return this.zzz.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j) {
        return j - this.zzl.zza() > this.zzp.zza();
    }
}
