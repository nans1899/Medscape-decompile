package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zziy implements zzkc {
    private static final zzji zzb = new zzix();
    private final zzji zza;

    public zziy() {
        this(new zzja(zzhx.zza(), zza()));
    }

    private zziy(zzji zzji) {
        this.zza = (zzji) zzic.zza(zzji, "messageInfoFactory");
    }

    public final <T> zzjz<T> zza(Class<T> cls) {
        zzkb.zza((Class<?>) cls);
        zzjf zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            if (zzhz.class.isAssignableFrom(cls)) {
                return zzjn.zza(zzkb.zzc(), zzhq.zza(), zzb2.zzc());
            }
            return zzjn.zza(zzkb.zza(), zzhq.zzb(), zzb2.zzc());
        } else if (zzhz.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzjl.zza(cls, zzb2, zzjr.zzb(), zzir.zzb(), zzkb.zzc(), zzhq.zza(), zzjg.zzb());
            }
            return zzjl.zza(cls, zzb2, zzjr.zzb(), zzir.zzb(), zzkb.zzc(), (zzho<?>) null, zzjg.zzb());
        } else if (zza(zzb2)) {
            return zzjl.zza(cls, zzb2, zzjr.zza(), zzir.zza(), zzkb.zza(), zzhq.zzb(), zzjg.zza());
        } else {
            return zzjl.zza(cls, zzb2, zzjr.zza(), zzir.zza(), zzkb.zzb(), (zzho<?>) null, zzjg.zza());
        }
    }

    private static boolean zza(zzjf zzjf) {
        return zzjf.zza() == zzjw.zza;
    }

    private static zzji zza() {
        try {
            return (zzji) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzb;
        }
    }
}
