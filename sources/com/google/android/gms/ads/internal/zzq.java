package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzacm;
import com.google.android.gms.internal.ads.zzayp;
import com.google.android.gms.internal.ads.zzbbx;
import com.google.android.gms.internal.ads.zzdgo;
import com.google.android.gms.internal.ads.zzvg;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzq {
    private final String zzbqu;
    private final Map<String, String> zzbqv = new TreeMap();
    private String zzbqw;
    private String zzbqx;
    private final Context zzvr;

    public zzq(Context context, String str) {
        this.zzvr = context.getApplicationContext();
        this.zzbqu = str;
    }

    public final String zzlq() {
        return this.zzbqx;
    }

    public final String getQuery() {
        return this.zzbqw;
    }

    public final String zzlr() {
        return this.zzbqu;
    }

    public final Map<String, String> zzls() {
        return this.zzbqv;
    }

    public final void zza(zzvg zzvg, zzbbx zzbbx) {
        this.zzbqw = zzvg.zzchg.zzbqw;
        Bundle bundle = zzvg.zzchi != null ? zzvg.zzchi.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle != null) {
            String str = zzacm.zzdaf.get();
            for (String str2 : bundle.keySet()) {
                if (str.equals(str2)) {
                    this.zzbqx = bundle.getString(str2);
                } else if (str2.startsWith("csa_")) {
                    this.zzbqv.put(str2.substring(4), bundle.getString(str2));
                }
            }
            this.zzbqv.put("SDKVersion", zzbbx.zzbre);
            if (zzacm.zzdad.get().booleanValue()) {
                try {
                    Bundle zza = zzdgo.zza(this.zzvr, new JSONArray(zzacm.zzdae.get()));
                    for (String str3 : zza.keySet()) {
                        this.zzbqv.put(str3, zza.get(str3).toString());
                    }
                } catch (JSONException e) {
                    zzayp.zzc("Flag gads:afs:csa_tcf_data_to_collect not a valid JSON array", e);
                }
            }
        }
    }
}
