package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzhq {
    private static final zzho<?> zza = new zzhn();
    private static final zzho<?> zzb = zzc();

    private static zzho<?> zzc() {
        try {
            return (zzho) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzho<?> zza() {
        return zza;
    }

    static zzho<?> zzb() {
        zzho<?> zzho = zzb;
        if (zzho != null) {
            return zzho;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
