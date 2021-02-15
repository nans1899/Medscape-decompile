package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaat extends zzaaq<String> {
    zzaat(int i, String str, String str2) {
        super(i, str, str2, (zzaap) null);
    }

    public final /* synthetic */ Object zza(Bundle bundle) {
        String valueOf = String.valueOf(getKey());
        if (!bundle.containsKey(valueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(valueOf) : new String("com.google.android.gms.ads.flag."))) {
            return (String) zzrf();
        }
        String valueOf2 = String.valueOf(getKey());
        return bundle.getString(valueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(valueOf2) : new String("com.google.android.gms.ads.flag."));
    }

    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Object obj) {
        editor.putString(getKey(), (String) obj);
    }

    public final /* synthetic */ Object zzb(JSONObject jSONObject) {
        return jSONObject.optString(getKey(), (String) zzrf());
    }

    public final /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(getKey(), (String) zzrf());
    }
}
