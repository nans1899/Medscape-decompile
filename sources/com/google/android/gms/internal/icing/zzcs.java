package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzcs {
    private static final Class<?> zzgg = zzo("libcore.io.Memory");
    private static final boolean zzgh = (zzo("org.robolectric.Robolectric") != null);

    static boolean zzal() {
        return zzgg != null && !zzgh;
    }

    static Class<?> zzam() {
        return zzgg;
    }

    private static <T> Class<T> zzo(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
