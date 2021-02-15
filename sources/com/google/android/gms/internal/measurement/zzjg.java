package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzjg {
    private static final zzje zza = zzc();
    private static final zzje zzb = new zzjd();

    static zzje zza() {
        return zza;
    }

    static zzje zzb() {
        return zzb;
    }

    private static zzje zzc() {
        try {
            return (zzje) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
