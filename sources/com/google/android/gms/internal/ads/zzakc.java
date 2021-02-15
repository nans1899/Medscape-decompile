package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final /* synthetic */ class zzakc {
    public static void zza(zzajz zzajz, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzajz.zzj(str, jSONObject.toString());
    }

    public static void zza(zzajz zzajz, String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        zzajz.zzdc(sb.toString());
    }

    public static void zzb(zzajz zzajz, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String valueOf = String.valueOf(sb.toString());
        zzayp.zzef(valueOf.length() != 0 ? "Dispatching AFMA event: ".concat(valueOf) : new String("Dispatching AFMA event: "));
        zzajz.zzdc(sb.toString());
    }

    public static void zza(zzajz zzajz, String str, Map map) {
        try {
            zzajz.zza(str, zzp.zzkr().zzj((Map<String, ?>) map));
        } catch (JSONException unused) {
            zzayp.zzfe("Could not convert parameters to JSON.");
        }
    }
}
