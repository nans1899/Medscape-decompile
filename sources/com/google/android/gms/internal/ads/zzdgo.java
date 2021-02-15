package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgo implements zzdgx<zzdgu<Bundle>> {
    private final Context zzaai;
    private final zzdzb zzgcz;

    zzdgo(zzdzb zzdzb, Context context) {
        this.zzgcz = zzdzb;
        this.zzaai = context;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        return this.zzgcz.zze(new zzdgr(this));
    }

    public static Bundle zza(Context context, JSONArray jSONArray) {
        int i;
        Object obj;
        SharedPreferences sharedPreferences;
        String str;
        Bundle bundle = new Bundle();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            String optString = optJSONObject.optString("bk");
            String optString2 = optJSONObject.optString("sk");
            int optInt = optJSONObject.optInt("type", -1);
            if (optInt == 0) {
                i = zzdgs.zzgya;
            } else if (optInt == 1) {
                i = zzdgs.zzgyb;
            } else if (optInt != 2) {
                i = 0;
            } else {
                i = zzdgs.zzgyc;
            }
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && i != 0) {
                String[] split = optString2.split("/");
                if (split.length > 2 || split.length == 0) {
                    obj = null;
                } else {
                    if (split.length == 1) {
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        str = split[0];
                    } else {
                        sharedPreferences = context.getSharedPreferences(split[0], 0);
                        str = split[1];
                    }
                    obj = sharedPreferences.getAll().get(str);
                }
                if (obj != null) {
                    int i3 = zzdgt.zzgye[i - 1];
                    if (i3 != 1) {
                        if (i3 != 2) {
                            if (i3 == 3 && (obj instanceof Boolean)) {
                                bundle.putBoolean(optString, ((Boolean) obj).booleanValue());
                            }
                        } else if (obj instanceof Integer) {
                            bundle.putInt(optString, ((Integer) obj).intValue());
                        } else if (obj instanceof Long) {
                            bundle.putLong(optString, ((Long) obj).longValue());
                        } else if (obj instanceof Float) {
                            bundle.putFloat(optString, ((Float) obj).floatValue());
                        }
                    } else if (obj instanceof String) {
                        bundle.putString(optString, (String) obj);
                    }
                }
            }
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdgu zzarz() throws Exception {
        String str = (String) zzwm.zzpx().zzd(zzabb.zzcwg);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new zzdgq(zza(this.zzaai, new JSONArray(str)));
        } catch (JSONException e) {
            zzayp.zzb("JSON parsing error", e);
            return null;
        }
    }
}
