package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhu {
    private static final zzhq<?> zzvb = new zzhs();
    private static final zzhq<?> zzvc = zzgj();

    private static zzhq<?> zzgj() {
        try {
            return (zzhq) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzhq<?> zzgk() {
        return zzvb;
    }

    static zzhq<?> zzgl() {
        zzhq<?> zzhq = zzvc;
        if (zzhq != null) {
            return zzhq;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
