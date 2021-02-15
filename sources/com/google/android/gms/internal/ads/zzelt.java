package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzelt {
    private static final zzelr zzimf = zzbia();
    private static final zzelr zzimg = new zzelu();

    static zzelr zzbhy() {
        return zzimf;
    }

    static zzelr zzbhz() {
        return zzimg;
    }

    private static zzelr zzbia() {
        try {
            return (zzelr) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
