package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpl {
    private final Context zzaai;
    private final zzbbx zzbot;
    private final zzayb zzbpw;
    private final Map<String, zzdpn> zzhgn = new HashMap();

    public zzdpl(Context context, zzbbx zzbbx, zzayb zzayb) {
        this.zzaai = context;
        this.zzbot = zzbbx;
        this.zzbpw = zzayb;
    }

    public final zzdpn zzgv(String str) {
        if (str == null) {
            return zzatv();
        }
        if (this.zzhgn.containsKey(str)) {
            return this.zzhgn.get(str);
        }
        zzdpn zzgw = zzgw(str);
        this.zzhgn.put(str, zzgw);
        return zzgw;
    }

    private final zzdpn zzatv() {
        return new zzdpn(this.zzaai, this.zzbpw.zzwt(), this.zzbpw.zzwv());
    }

    private final zzdpn zzgw(String str) {
        zzatx zzaa = zzatx.zzaa(this.zzaai);
        try {
            zzaa.setAppPackageName(str);
            zzayq zzayq = new zzayq();
            zzayq.zza(this.zzaai, str, false);
            zzayv zzayv = new zzayv(this.zzbpw.zzwt(), zzayq);
            return new zzdpn(zzaa, zzayv, new zzayi(zzbbg.zzyo(), zzayv));
        } catch (PackageManager.NameNotFoundException unused) {
            return zzatv();
        }
    }
}
