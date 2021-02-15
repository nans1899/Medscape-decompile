package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbag {
    private static zzae zzecm;
    private static final Object zzecn = new Object();
    @Deprecated
    private static final zzbak<Void> zzeco = new zzbaj();

    public zzbag(Context context) {
        zzbl(context.getApplicationContext() != null ? context.getApplicationContext() : context);
    }

    public static zzdzc<zzy> zzev(String str) {
        zzbcg zzbcg = new zzbcg();
        zzecm.zze(new zzbam(str, zzbcg));
        return zzbcg;
    }

    public final zzdzc<String> zza(int i, String str, Map<String, String> map, byte[] bArr) {
        String str2 = str;
        zzban zzban = new zzban((zzbaj) null);
        zzbai zzbai = new zzbai(this, str2, zzban);
        zzbbk zzbbk = new zzbbk((String) null);
        zzbal zzbal = new zzbal(this, i, str, zzban, zzbai, bArr, map, zzbbk);
        if (zzbbk.isEnabled()) {
            try {
                zzbbk.zza(str2, "GET", zzbal.getHeaders(), zzbal.zzg());
            } catch (zzl e) {
                zzayp.zzfe(e.getMessage());
            }
        }
        zzecm.zze(zzbal);
        return zzban;
    }

    public final zzdzc<String> zzc(String str, Map<String, String> map) {
        return zza(0, str, map, (byte[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzae zzbl(android.content.Context r3) {
        /*
            java.lang.Object r0 = zzecn
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzae r1 = zzecm     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0032
            com.google.android.gms.internal.ads.zzabb.initialize(r3)     // Catch:{ all -> 0x0036 }
            boolean r1 = com.google.android.gms.common.util.ClientLibraryUtils.isPackageSide()     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zzabb.zzctg     // Catch:{ all -> 0x0036 }
            com.google.android.gms.internal.ads.zzaax r2 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0036 }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x0036 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0036 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0024
            r1 = 1
            goto L_0x0025
        L_0x0024:
            r1 = 0
        L_0x0025:
            if (r1 == 0) goto L_0x002c
            com.google.android.gms.internal.ads.zzae r3 = com.google.android.gms.internal.ads.zzazz.zzbk(r3)     // Catch:{ all -> 0x0036 }
            goto L_0x0030
        L_0x002c:
            com.google.android.gms.internal.ads.zzae r3 = com.google.android.gms.internal.ads.zzbk.zza(r3)     // Catch:{ all -> 0x0036 }
        L_0x0030:
            zzecm = r3     // Catch:{ all -> 0x0036 }
        L_0x0032:
            com.google.android.gms.internal.ads.zzae r3 = zzecm     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return r3
        L_0x0036:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbag.zzbl(android.content.Context):com.google.android.gms.internal.ads.zzae");
    }
}
