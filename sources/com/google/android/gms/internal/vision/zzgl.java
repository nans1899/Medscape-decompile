package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgl {
    private static final Class<?> zzti = zzu("libcore.io.Memory");
    private static final boolean zztj = (zzu("org.robolectric.Robolectric") != null);

    static boolean zzel() {
        return zzti != null && !zztj;
    }

    static Class<?> zzem() {
        return zzti;
    }

    private static <T> Class<T> zzu(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
