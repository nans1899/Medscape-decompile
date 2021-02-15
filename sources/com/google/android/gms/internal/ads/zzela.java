package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzela implements zzemi {
    private static final zzelk zzilf = new zzekz();
    private final zzelk zzile;

    public zzela() {
        this(new zzelc(zzeka.zzbgo(), zzbho()));
    }

    private zzela(zzelk zzelk) {
        this.zzile = (zzelk) zzekb.zza(zzelk, "messageInfoFactory");
    }

    public final <T> zzemf<T> zzg(Class<T> cls) {
        zzemh.zzi(cls);
        zzelh zzf = this.zzile.zzf(cls);
        if (zzf.zzbht()) {
            if (zzejz.class.isAssignableFrom(cls)) {
                return zzelp.zza(zzemh.zzbij(), zzejp.zzbfw(), zzf.zzbhu());
            }
            return zzelp.zza(zzemh.zzbih(), zzejp.zzbfx(), zzf.zzbhu());
        } else if (zzejz.class.isAssignableFrom(cls)) {
            if (zza(zzf)) {
                return zzeln.zza(cls, zzf, zzelt.zzbhz(), zzekt.zzbhm(), zzemh.zzbij(), zzejp.zzbfw(), zzeli.zzbhw());
            }
            return zzeln.zza(cls, zzf, zzelt.zzbhz(), zzekt.zzbhm(), zzemh.zzbij(), (zzejo<?>) null, zzeli.zzbhw());
        } else if (zza(zzf)) {
            return zzeln.zza(cls, zzf, zzelt.zzbhy(), zzekt.zzbhl(), zzemh.zzbih(), zzejp.zzbfx(), zzeli.zzbhv());
        } else {
            return zzeln.zza(cls, zzf, zzelt.zzbhy(), zzekt.zzbhl(), zzemh.zzbii(), (zzejo<?>) null, zzeli.zzbhv());
        }
    }

    private static boolean zza(zzelh zzelh) {
        return zzelh.zzbhs() == zzejz.zze.zzijs;
    }

    private static zzelk zzbho() {
        try {
            return (zzelk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzilf;
        }
    }
}
