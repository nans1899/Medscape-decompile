package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzhz;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public class zzhm {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzhm zzc;
    private static volatile zzhm zzd;
    private static final zzhm zze = new zzhm(true);
    private final Map<zza, zzhz.zzd<?, ?>> zzf;

    public static zzhm zza() {
        zzhm zzhm = zzc;
        if (zzhm == null) {
            synchronized (zzhm.class) {
                zzhm = zzc;
                if (zzhm == null) {
                    zzhm = zze;
                    zzc = zzhm;
                }
            }
        }
        return zzhm;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
    private static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza2 = (zza) obj;
            if (this.zza == zza2.zza && this.zzb == zza2.zzb) {
                return true;
            }
            return false;
        }
    }

    public static zzhm zzb() {
        Class<zzhm> cls = zzhm.class;
        zzhm zzhm = zzd;
        if (zzhm != null) {
            return zzhm;
        }
        synchronized (cls) {
            zzhm zzhm2 = zzd;
            if (zzhm2 != null) {
                return zzhm2;
            }
            zzhm zza2 = zzhy.zza(cls);
            zzd = zza2;
            return zza2;
        }
    }

    public final <ContainingType extends zzjh> zzhz.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzf.get(new zza(containingtype, i));
    }

    zzhm() {
        this.zzf = new HashMap();
    }

    private zzhm(boolean z) {
        this.zzf = Collections.emptyMap();
    }
}
