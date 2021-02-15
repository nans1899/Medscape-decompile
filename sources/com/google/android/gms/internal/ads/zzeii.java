package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeii {
    private static final Class<?> zziei = zzht("libcore.io.Memory");
    private static final boolean zziej = (zzht("org.robolectric.Robolectric") != null);

    static boolean zzbdz() {
        return zziei != null && !zziej;
    }

    static Class<?> zzbea() {
        return zziei;
    }

    private static <T> Class<T> zzht(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
