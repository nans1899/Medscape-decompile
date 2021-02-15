package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdp {
    private static final zzdn<?> zzhg = new zzdq();
    private static final zzdn<?> zzhh = zzba();

    private static zzdn<?> zzba() {
        try {
            return (zzdn) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzdn<?> zzbb() {
        return zzhg;
    }

    static zzdn<?> zzbc() {
        zzdn<?> zzdn = zzhh;
        if (zzdn != null) {
            return zzdn;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
