package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeli {
    private static final zzelg zzill = zzbhx();
    private static final zzelg zzilm = new zzelf();

    static zzelg zzbhv() {
        return zzill;
    }

    static zzelg zzbhw() {
        return zzilm;
    }

    private static zzelg zzbhx() {
        try {
            return (zzelg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
