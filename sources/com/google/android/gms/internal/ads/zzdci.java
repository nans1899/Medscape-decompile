package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdci implements zzdgx<zzdcg> {
    private final String zzdqu;
    private final zzcku zzgij;
    private final zzdzb zzguy;

    public zzdci(String str, zzdzb zzdzb, zzcku zzcku) {
        this.zzdqu = str;
        this.zzguy = zzdzb;
        this.zzgij = zzcku;
    }

    public final zzdzc<zzdcg> zzarj() {
        if (new BigInteger(this.zzdqu).equals(BigInteger.ONE)) {
            if (!zzdwf.zzas((String) zzwm.zzpx().zzd(zzabb.zzcqa))) {
                return this.zzguy.zze(new zzdcl(this));
            }
        }
        return zzdyq.zzaf(new zzdcg(new Bundle()));
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001e A[Catch:{ zzdos -> 0x002b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.os.Bundle zzb(com.google.android.gms.internal.ads.zzdpa r3) {
        /*
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            com.google.android.gms.internal.ads.zzapv r1 = r3.zzub()     // Catch:{ zzdos -> 0x0018 }
            if (r1 == 0) goto L_0x0018
            java.lang.String r1 = "sdk_version"
            com.google.android.gms.internal.ads.zzapv r2 = r3.zzub()     // Catch:{ zzdos -> 0x0018 }
            java.lang.String r2 = r2.toString()     // Catch:{ zzdos -> 0x0018 }
            r0.putString(r1, r2)     // Catch:{ zzdos -> 0x0018 }
        L_0x0018:
            com.google.android.gms.internal.ads.zzapv r1 = r3.zzua()     // Catch:{ zzdos -> 0x002b }
            if (r1 == 0) goto L_0x002b
            java.lang.String r1 = "adapter_version"
            com.google.android.gms.internal.ads.zzapv r3 = r3.zzua()     // Catch:{ zzdos -> 0x002b }
            java.lang.String r3 = r3.toString()     // Catch:{ zzdos -> 0x002b }
            r0.putString(r1, r3)     // Catch:{ zzdos -> 0x002b }
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdci.zzb(com.google.android.gms.internal.ads.zzdpa):android.os.Bundle");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdcg zzarl() throws Exception {
        List<String> asList = Arrays.asList(((String) zzwm.zzpx().zzd(zzabb.zzcqa)).split(";"));
        Bundle bundle = new Bundle();
        for (String str : asList) {
            try {
                bundle.putBundle(str, zzb(this.zzgij.zzd(str, new JSONObject())));
            } catch (zzdos unused) {
            }
        }
        return new zzdcg(bundle);
    }
}
