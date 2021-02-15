package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdv {
    public final boolean zzeip;
    public final int zzeiq;
    public final int zzeir;
    public final int zzeis;
    private final String zzeit;
    public final int zzeiu;
    public final int zzeiv;
    public final int zzeiw;
    public final int zzeix;
    public final boolean zzeiy;
    public final int zzeiz;

    public zzbdv(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzeip = zza(jSONObject, "aggressive_media_codec_release", zzabb.zzcmt);
        this.zzeiq = zzb(jSONObject, "byte_buffer_precache_limit", zzabb.zzcmb);
        this.zzeir = zzb(jSONObject, "exo_cache_buffer_size", zzabb.zzcmi);
        this.zzeis = zzb(jSONObject, "exo_connect_timeout_millis", zzabb.zzclx);
        this.zzeit = zzc(jSONObject, "exo_player_version", zzabb.zzclw);
        this.zzeiu = zzb(jSONObject, "exo_read_timeout_millis", zzabb.zzcly);
        this.zzeiv = zzb(jSONObject, "load_check_interval_bytes", zzabb.zzclz);
        this.zzeiw = zzb(jSONObject, "player_precache_limit", zzabb.zzcma);
        this.zzeix = zzb(jSONObject, "socket_receive_buffer_size", zzabb.zzcmc);
        this.zzeiy = zza(jSONObject, "use_cache_data_source", zzabb.zzctc);
        this.zzeiz = zzb(jSONObject, "min_retry_count", zzabb.zzcme);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzaaq, com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(org.json.JSONObject r1, java.lang.String r2, com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r3) {
        /*
            com.google.android.gms.internal.ads.zzaax r0 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r3 = r0.zzd(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            boolean r1 = zza((org.json.JSONObject) r1, (java.lang.String) r2, (boolean) r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbdv.zza(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzaaq):boolean");
    }

    private static boolean zza(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.ads.zzaaq, com.google.android.gms.internal.ads.zzaaq<java.lang.Integer>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzb(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.ads.zzaaq<java.lang.Integer> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            int r0 = r0.getInt(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.ads.zzaax r0 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbdv.zzb(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzaaq):int");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.gms.internal.ads.zzaaq, com.google.android.gms.internal.ads.zzaaq<java.lang.String>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzc(org.json.JSONObject r0, java.lang.String r1, com.google.android.gms.internal.ads.zzaaq<java.lang.String> r2) {
        /*
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x0007 }
            return r0
        L_0x0007:
            com.google.android.gms.internal.ads.zzaax r0 = com.google.android.gms.internal.ads.zzwm.zzpx()
            java.lang.Object r0 = r0.zzd(r2)
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbdv.zzc(org.json.JSONObject, java.lang.String, com.google.android.gms.internal.ads.zzaaq):java.lang.String");
    }
}
