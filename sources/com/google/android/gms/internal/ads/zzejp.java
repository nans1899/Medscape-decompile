package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejp {
    private static final zzejo<?> zzige = new zzejq();
    private static final zzejo<?> zzigf = zzbfv();

    private static zzejo<?> zzbfv() {
        try {
            return (zzejo) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzejo<?> zzbfw() {
        return zzige;
    }

    static zzejo<?> zzbfx() {
        zzejo<?> zzejo = zzigf;
        if (zzejo != null) {
            return zzejo;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
