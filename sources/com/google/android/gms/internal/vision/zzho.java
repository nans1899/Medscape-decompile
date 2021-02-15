package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class zzho {
    private static volatile boolean zzur = false;
    private static boolean zzus = true;
    private static volatile zzho zzut;
    private static volatile zzho zzuu;
    private static final zzho zzuv = new zzho(true);
    private final Map<zza, zzid.zzg<?, ?>> zzuw;

    public static zzho zzge() {
        return new zzho();
    }

    public static zzho zzgf() {
        zzho zzho = zzut;
        if (zzho == null) {
            synchronized (zzho.class) {
                zzho = zzut;
                if (zzho == null) {
                    zzho = zzuv;
                    zzut = zzho;
                }
            }
        }
        return zzho;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static final class zza {
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

    public static zzho zzgg() {
        Class<zzho> cls = zzho.class;
        zzho zzho = zzuu;
        if (zzho != null) {
            return zzho;
        }
        synchronized (cls) {
            zzho zzho2 = zzuu;
            if (zzho2 != null) {
                return zzho2;
            }
            zzho zzc = zzic.zzc(cls);
            zzuu = zzc;
            return zzc;
        }
    }

    public final <ContainingType extends zzjn> zzid.zzg<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzuw.get(new zza(containingtype, i));
    }

    public final void zza(zzid.zzg<?, ?> zzg) {
        this.zzuw.put(new zza(zzg.zzyp, zzg.zzyr.number), zzg);
    }

    zzho() {
        this.zzuw = new HashMap();
    }

    private zzho(boolean z) {
        this.zzuw = Collections.emptyMap();
    }
}
