package com.google.android.gms.internal.vision;

import com.google.android.gms.vision.L;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzr {
    private static final Object sLock = new Object();
    private static final HashMap<String, Integer> zzcf = new HashMap<>();

    public static boolean zza(String str, String str2) {
        synchronized (sLock) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(str2);
            String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            int intValue = zzcf.containsKey(concat) ? zzcf.get(concat).intValue() : 0;
            if ((intValue & 1) != 0) {
                return true;
            }
            try {
                System.loadLibrary(str2);
                zzcf.put(concat, Integer.valueOf(intValue | 1));
                return true;
            } catch (UnsatisfiedLinkError e) {
                if ((intValue & 4) == 0) {
                    L.e(e, "System.loadLibrary failed: %s", str2);
                    zzcf.put(concat, Integer.valueOf(intValue | 4));
                }
                return false;
            }
        }
    }
}
