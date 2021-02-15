package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class zzdo {
    private static volatile boolean zzhb = false;
    private static boolean zzhc = true;
    private static volatile zzdo zzhd;
    private static final zzdo zzhe = new zzdo(true);
    private final Map<Object, Object> zzhf;

    public static zzdo zzaz() {
        zzdo zzdo = zzhd;
        if (zzdo == null) {
            synchronized (zzdo.class) {
                zzdo = zzhd;
                if (zzdo == null) {
                    zzdo = zzhe;
                    zzhd = zzdo;
                }
            }
        }
        return zzdo;
    }

    zzdo() {
        this.zzhf = new HashMap();
    }

    private zzdo(boolean z) {
        this.zzhf = Collections.emptyMap();
    }
}
