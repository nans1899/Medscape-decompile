package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzayq implements zzayr {
    private final Object lock = new Object();
    private SharedPreferences zzcln;
    private boolean zzdqy = true;
    private boolean zzdrl = false;
    private String zzdro = "";
    private boolean zzdsx = true;
    private boolean zzdtj = true;
    private boolean zzeag;
    private final List<Runnable> zzeah = new ArrayList();
    private zzdzc<?> zzeai;
    private zzrk zzeaj = null;
    private SharedPreferences.Editor zzeak;
    private boolean zzeal = false;
    private String zzeam;
    private String zzean;
    private long zzeao = 0;
    private long zzeap = 0;
    private long zzeaq = 0;
    private int zzear = -1;
    private int zzeas = 0;
    private Set<String> zzeat = Collections.emptySet();
    private JSONObject zzeau = new JSONObject();
    private String zzeav = null;
    private int zzeaw = -1;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (r4.length() == 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r4 = "admob__".concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        r4 = new java.lang.String("admob__");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        r2.zzeai = com.google.android.gms.internal.ads.zzbbz.zzeep.zzg(new com.google.android.gms.internal.ads.zzayt(r2, r3, r4));
        r2.zzeag = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        if (r4 != null) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        r4 = "admob";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.content.Context r3, java.lang.String r4, boolean r5) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            android.content.SharedPreferences r1 = r2.zzcln     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            return
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            if (r4 != 0) goto L_0x000f
            java.lang.String r4 = "admob"
            goto L_0x0025
        L_0x000f:
            java.lang.String r0 = "admob__"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r1 = r4.length()
            if (r1 == 0) goto L_0x0020
            java.lang.String r4 = r0.concat(r4)
            goto L_0x0025
        L_0x0020:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r0)
        L_0x0025:
            com.google.android.gms.internal.ads.zzdzb r0 = com.google.android.gms.internal.ads.zzbbz.zzeep
            com.google.android.gms.internal.ads.zzayt r1 = new com.google.android.gms.internal.ads.zzayt
            r1.<init>(r2, r3, r4)
            com.google.android.gms.internal.ads.zzdzc r3 = r0.zzg(r1)
            r2.zzeai = r3
            r2.zzeag = r5
            return
        L_0x0035:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0035 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayq.zza(android.content.Context, java.lang.String, boolean):void");
    }

    private final void zzxb() {
        zzdzc<?> zzdzc = this.zzeai;
        if (zzdzc != null && !zzdzc.isDone()) {
            try {
                this.zzeai.get(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                zzayp.zzd("Interrupted while waiting for preferences loaded.", e);
            } catch (CancellationException | ExecutionException | TimeoutException e2) {
                zzayp.zzc("Fail to initialize AdSharedPreferenceManager.", e2);
            }
        }
    }

    private final Bundle zzxc() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.lock) {
            bundle.putBoolean("use_https", this.zzdqy);
            bundle.putBoolean("content_url_opted_out", this.zzdsx);
            bundle.putBoolean("content_vertical_opted_out", this.zzdtj);
            bundle.putBoolean("auto_collect_location", this.zzdrl);
            bundle.putInt("version_code", this.zzeas);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzeat.toArray(new String[0]));
            bundle.putString("app_settings_json", this.zzdro);
            bundle.putLong("app_settings_last_update_ms", this.zzeao);
            bundle.putLong("app_last_background_time_ms", this.zzeap);
            bundle.putInt("request_in_session_count", this.zzear);
            bundle.putLong("first_ad_req_time_ms", this.zzeaq);
            bundle.putString("native_advanced_settings", this.zzeau.toString());
            bundle.putString("display_cutout", this.zzeav);
            bundle.putInt("app_measurement_npa", this.zzeaw);
            if (this.zzeam != null) {
                bundle.putString("content_url_hashes", this.zzeam);
            }
            if (this.zzean != null) {
                bundle.putString("content_vertical_hashes", this.zzean);
            }
        }
        return bundle;
    }

    private final void zzc(Bundle bundle) {
        zzbbz.zzeep.execute(new zzays(this));
    }

    public final zzrk zzxd() {
        if (!this.zzeag) {
            return null;
        }
        if ((zzxe() && zzxg()) || !zzacq.zzdar.get().booleanValue()) {
            return null;
        }
        synchronized (this.lock) {
            if (Looper.getMainLooper() == null) {
                return null;
            }
            if (this.zzeaj == null) {
                this.zzeaj = new zzrk();
            }
            this.zzeaj.zzmi();
            zzayp.zzfd("start fetching content...");
            zzrk zzrk = this.zzeaj;
            return zzrk;
        }
    }

    public final void zzao(boolean z) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzdsx != z) {
                this.zzdsx = z;
                if (this.zzeak != null) {
                    this.zzeak.putBoolean("content_url_opted_out", z);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzdsx);
                bundle.putBoolean("content_vertical_opted_out", this.zzdtj);
                zzc(bundle);
            }
        }
    }

    public final boolean zzxe() {
        boolean z;
        zzxb();
        synchronized (this.lock) {
            z = this.zzdsx;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzej(java.lang.String r4) {
        /*
            r3 = this;
            r3.zzxb()
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzeam     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzeam = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_url_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zzc(r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayq.zzej(java.lang.String):void");
    }

    public final String zzxf() {
        String str;
        zzxb();
        synchronized (this.lock) {
            str = this.zzeam;
        }
        return str;
    }

    public final void zzap(boolean z) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzdtj != z) {
                this.zzdtj = z;
                if (this.zzeak != null) {
                    this.zzeak.putBoolean("content_vertical_opted_out", z);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("content_url_opted_out", this.zzdsx);
                bundle.putBoolean("content_vertical_opted_out", this.zzdtj);
                zzc(bundle);
            }
        }
    }

    public final boolean zzxg() {
        boolean z;
        zzxb();
        synchronized (this.lock) {
            z = this.zzdtj;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzek(java.lang.String r4) {
        /*
            r3 = this;
            r3.zzxb()
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            if (r4 == 0) goto L_0x0032
            java.lang.String r1 = r3.zzean     // Catch:{ all -> 0x0034 }
            boolean r1 = r4.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            r3.zzean = r4     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0023
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            android.content.SharedPreferences$Editor r1 = r3.zzeak     // Catch:{ all -> 0x0034 }
            r1.apply()     // Catch:{ all -> 0x0034 }
        L_0x0023:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.lang.String r2 = "content_vertical_hashes"
            r1.putString(r2, r4)     // Catch:{ all -> 0x0034 }
            r3.zzc(r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayq.zzek(java.lang.String):void");
    }

    public final String zzxh() {
        String str;
        zzxb();
        synchronized (this.lock) {
            str = this.zzean;
        }
        return str;
    }

    public final void zzaq(boolean z) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzdrl != z) {
                this.zzdrl = z;
                if (this.zzeak != null) {
                    this.zzeak.putBoolean("auto_collect_location", z);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("auto_collect_location", z);
                zzc(bundle);
            }
        }
    }

    public final boolean zzxi() {
        boolean z;
        zzxb();
        synchronized (this.lock) {
            z = this.zzdrl;
        }
        return z;
    }

    public final void zzdf(int i) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzeas != i) {
                this.zzeas = i;
                if (this.zzeak != null) {
                    this.zzeak.putInt("version_code", i);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("version_code", i);
                zzc(bundle);
            }
        }
    }

    public final int zzxj() {
        int i;
        zzxb();
        synchronized (this.lock) {
            i = this.zzeas;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzel(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzxb()
            java.lang.Object r0 = r5.lock
            monitor-enter(r0)
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzp.zzky()     // Catch:{ all -> 0x0060 }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x0060 }
            r5.zzeao = r1     // Catch:{ all -> 0x0060 }
            if (r6 == 0) goto L_0x005e
            java.lang.String r3 = r5.zzdro     // Catch:{ all -> 0x0060 }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x001b
            goto L_0x005e
        L_0x001b:
            r5.zzdro = r6     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzeak     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x0034
            android.content.SharedPreferences$Editor r3 = r5.zzeak     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzeak     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_last_update_ms"
            r3.putLong(r4, r1)     // Catch:{ all -> 0x0060 }
            android.content.SharedPreferences$Editor r3 = r5.zzeak     // Catch:{ all -> 0x0060 }
            r3.apply()     // Catch:{ all -> 0x0060 }
        L_0x0034:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x0060 }
            r3.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = "app_settings_json"
            r3.putString(r4, r6)     // Catch:{ all -> 0x0060 }
            java.lang.String r6 = "app_settings_last_update_ms"
            r3.putLong(r6, r1)     // Catch:{ all -> 0x0060 }
            r5.zzc(r3)     // Catch:{ all -> 0x0060 }
            java.util.List<java.lang.Runnable> r6 = r5.zzeah     // Catch:{ all -> 0x0060 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x0060 }
        L_0x004c:
            boolean r1 = r6.hasNext()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x005c
            java.lang.Object r1 = r6.next()     // Catch:{ all -> 0x0060 }
            java.lang.Runnable r1 = (java.lang.Runnable) r1     // Catch:{ all -> 0x0060 }
            r1.run()     // Catch:{ all -> 0x0060 }
            goto L_0x004c
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x005e:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzayq.zzel(java.lang.String):void");
    }

    public final zzaxy zzxk() {
        zzaxy zzaxy;
        zzxb();
        synchronized (this.lock) {
            zzaxy = new zzaxy(this.zzdro, this.zzeao);
        }
        return zzaxy;
    }

    public final void zzb(Runnable runnable) {
        this.zzeah.add(runnable);
    }

    public final void zzez(long j) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzeap != j) {
                this.zzeap = j;
                if (this.zzeak != null) {
                    this.zzeak.putLong("app_last_background_time_ms", j);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("app_last_background_time_ms", j);
                zzc(bundle);
            }
        }
    }

    public final long zzxl() {
        long j;
        zzxb();
        synchronized (this.lock) {
            j = this.zzeap;
        }
        return j;
    }

    public final void zzdg(int i) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzear != i) {
                this.zzear = i;
                if (this.zzeak != null) {
                    this.zzeak.putInt("request_in_session_count", i);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("request_in_session_count", i);
                zzc(bundle);
            }
        }
    }

    public final int zzxm() {
        int i;
        zzxb();
        synchronized (this.lock) {
            i = this.zzear;
        }
        return i;
    }

    public final void zzfa(long j) {
        zzxb();
        synchronized (this.lock) {
            if (this.zzeaq != j) {
                this.zzeaq = j;
                if (this.zzeak != null) {
                    this.zzeak.putLong("first_ad_req_time_ms", j);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putLong("first_ad_req_time_ms", j);
                zzc(bundle);
            }
        }
    }

    public final long zzxn() {
        long j;
        zzxb();
        synchronized (this.lock) {
            j = this.zzeaq;
        }
        return j;
    }

    public final void zza(String str, String str2, boolean z) {
        zzxb();
        synchronized (this.lock) {
            JSONArray optJSONArray = this.zzeau.optJSONArray(str);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            int length = optJSONArray.length();
            int i = 0;
            while (true) {
                if (i < optJSONArray.length()) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        if (!str2.equals(optJSONObject.optString("template_id"))) {
                            i++;
                        } else if (!z || !optJSONObject.optBoolean("uses_media_view", false)) {
                            length = i;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", zzp.zzky().currentTimeMillis());
                optJSONArray.put(length, jSONObject);
                this.zzeau.put(str, optJSONArray);
            } catch (JSONException e) {
                zzayp.zzd("Could not update native advanced settings", e);
            }
            if (this.zzeak != null) {
                this.zzeak.putString("native_advanced_settings", this.zzeau.toString());
                this.zzeak.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzeau.toString());
            zzc(bundle);
        }
    }

    public final JSONObject zzxo() {
        JSONObject jSONObject;
        zzxb();
        synchronized (this.lock) {
            jSONObject = this.zzeau;
        }
        return jSONObject;
    }

    public final void zzxp() {
        zzxb();
        synchronized (this.lock) {
            this.zzeau = new JSONObject();
            if (this.zzeak != null) {
                this.zzeak.remove("native_advanced_settings");
                this.zzeak.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zzc(bundle);
        }
    }

    public final String zzxq() {
        String str;
        zzxb();
        synchronized (this.lock) {
            str = this.zzeav;
        }
        return str;
    }

    public final void zzem(String str) {
        zzxb();
        synchronized (this.lock) {
            if (!TextUtils.equals(this.zzeav, str)) {
                this.zzeav = str;
                if (this.zzeak != null) {
                    this.zzeak.putString("display_cutout", str);
                    this.zzeak.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putString("display_cutout", str);
                zzc(bundle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(Context context, String str) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        synchronized (this.lock) {
            this.zzcln = sharedPreferences;
            this.zzeak = edit;
            if (PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                z = true;
            }
            this.zzeal = z;
            this.zzdqy = this.zzcln.getBoolean("use_https", this.zzdqy);
            this.zzdsx = this.zzcln.getBoolean("content_url_opted_out", this.zzdsx);
            this.zzeam = this.zzcln.getString("content_url_hashes", this.zzeam);
            this.zzdrl = this.zzcln.getBoolean("auto_collect_location", this.zzdrl);
            this.zzdtj = this.zzcln.getBoolean("content_vertical_opted_out", this.zzdtj);
            this.zzean = this.zzcln.getString("content_vertical_hashes", this.zzean);
            this.zzeas = this.zzcln.getInt("version_code", this.zzeas);
            this.zzdro = this.zzcln.getString("app_settings_json", this.zzdro);
            this.zzeao = this.zzcln.getLong("app_settings_last_update_ms", this.zzeao);
            this.zzeap = this.zzcln.getLong("app_last_background_time_ms", this.zzeap);
            this.zzear = this.zzcln.getInt("request_in_session_count", this.zzear);
            this.zzeaq = this.zzcln.getLong("first_ad_req_time_ms", this.zzeaq);
            this.zzeat = this.zzcln.getStringSet("never_pool_slots", this.zzeat);
            this.zzeav = this.zzcln.getString("display_cutout", this.zzeav);
            this.zzeaw = this.zzcln.getInt("app_measurement_npa", this.zzeaw);
            try {
                this.zzeau = new JSONObject(this.zzcln.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzayp.zzd("Could not convert native advanced settings to json object", e);
            }
            zzc(zzxc());
        }
    }
}
