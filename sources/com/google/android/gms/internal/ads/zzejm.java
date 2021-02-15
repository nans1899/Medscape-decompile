package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzejm {
    private static volatile boolean zzify = false;
    private static boolean zzifz = true;
    private static volatile zzejm zziga;
    private static volatile zzejm zzigb;
    private static final zzejm zzigc = new zzejm(true);
    private final Map<zza, zzejz.zzf<?, ?>> zzigd;

    public static zzejm zzbft() {
        zzejm zzejm = zziga;
        if (zzejm == null) {
            synchronized (zzejm.class) {
                zzejm = zziga;
                if (zzejm == null) {
                    zzejm = zzigc;
                    zziga = zzejm;
                }
            }
        }
        return zzejm;
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.object == zza.object && this.number == zza.number) {
                return true;
            }
            return false;
        }
    }

    public static zzejm zzbfu() {
        Class<zzejm> cls = zzejm.class;
        zzejm zzejm = zzigb;
        if (zzejm != null) {
            return zzejm;
        }
        synchronized (cls) {
            zzejm zzejm2 = zzigb;
            if (zzejm2 != null) {
                return zzejm2;
            }
            zzejm zzc = zzejx.zzc(cls);
            zzigb = zzc;
            return zzc;
        }
    }

    public final <ContainingType extends zzelj> zzejz.zzf<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzigd.get(new zza(containingtype, i));
    }

    zzejm() {
        this.zzigd = new HashMap();
    }

    private zzejm(boolean z) {
        this.zzigd = Collections.emptyMap();
    }
}
