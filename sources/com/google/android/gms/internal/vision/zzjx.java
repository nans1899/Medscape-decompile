package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzjx {
    private static final zzjv zzabb = zzii();
    private static final zzjv zzabc = new zzju();

    static zzjv zzig() {
        return zzabb;
    }

    static zzjv zzih() {
        return zzabc;
    }

    private static zzjv zzii() {
        try {
            return (zzjv) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
