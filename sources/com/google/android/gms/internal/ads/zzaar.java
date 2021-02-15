package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaar extends zzaaq<Long> {
    zzaar(int i, String str, Long l) {
        super(1, str, l, (zzaap) null);
    }

    public final /* synthetic */ Object zza(Bundle bundle) {
        String valueOf = String.valueOf(getKey());
        if (!bundle.containsKey(valueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(valueOf) : new String("com.google.android.gms.ads.flag."))) {
            return (Long) zzrf();
        }
        String valueOf2 = String.valueOf(getKey());
        return Long.valueOf(bundle.getLong(valueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(valueOf2) : new String("com.google.android.gms.ads.flag.")));
    }

    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Object obj) {
        editor.putLong(getKey(), ((Long) obj).longValue());
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(getKey(), ((Long) zzrf()).longValue()));
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzrf()).longValue()));
    }
}
