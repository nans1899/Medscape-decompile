package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzji {
    private static final zzjg zzaag = zzic();
    private static final zzjg zzaah = new zzjj();

    static zzjg zzia() {
        return zzaag;
    }

    static zzjg zzib() {
        return zzaah;
    }

    private static zzjg zzic() {
        try {
            return (zzjg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
