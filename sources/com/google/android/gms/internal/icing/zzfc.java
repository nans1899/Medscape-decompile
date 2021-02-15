package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfc {
    private static final zzfa zzml = zzcn();
    private static final zzfa zzmm = new zzfd();

    static zzfa zzcl() {
        return zzml;
    }

    static zzfa zzcm() {
        return zzmm;
    }

    private static zzfa zzcn() {
        try {
            return (zzfa) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
