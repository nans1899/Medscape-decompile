package com.google.android.gms.internal.ads;

import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzdvz {
    private static final Logger logger = Logger.getLogger(zzdvz.class.getName());
    private static final zzdwa zzhpc = new zza();

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    static final class zza {
        private zza() {
        }
    }

    private zzdvz() {
    }

    static String zzhi(@NullableDecl String str) {
        return str == null ? "" : str;
    }

    static boolean zzhh(@NullableDecl String str) {
        return str == null || str.isEmpty();
    }

    static String emptyToNull(@NullableDecl String str) {
        if (zzhh(str)) {
            return null;
        }
        return str;
    }
}
