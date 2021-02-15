package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzja implements zzke {
    private static final zzjk zzzy = new zzjd();
    private final zzjk zzzx;

    public zzja() {
        this(new zzjc(zzib.zzgq(), zzhw()));
    }

    private zzja(zzjk zzjk) {
        this.zzzx = (zzjk) zzie.zza(zzjk, "messageInfoFactory");
    }

    public final <T> zzkf<T> zze(Class<T> cls) {
        zzkh.zzg(cls);
        zzjl zzb = this.zzzx.zzb(cls);
        if (zzb.zzie()) {
            if (zzid.class.isAssignableFrom(cls)) {
                return zzjt.zza(zzkh.zziu(), zzhu.zzgk(), zzb.zzif());
            }
            return zzjt.zza(zzkh.zzis(), zzhu.zzgl(), zzb.zzif());
        } else if (zzid.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzjr.zza(cls, zzb, zzjx.zzih(), zzix.zzhv(), zzkh.zziu(), zzhu.zzgk(), zzji.zzib());
            }
            return zzjr.zza(cls, zzb, zzjx.zzih(), zzix.zzhv(), zzkh.zziu(), (zzhq<?>) null, zzji.zzib());
        } else if (zza(zzb)) {
            return zzjr.zza(cls, zzb, zzjx.zzig(), zzix.zzhu(), zzkh.zzis(), zzhu.zzgl(), zzji.zzia());
        } else {
            return zzjr.zza(cls, zzb, zzjx.zzig(), zzix.zzhu(), zzkh.zzit(), (zzhq<?>) null, zzji.zzia());
        }
    }

    private static boolean zza(zzjl zzjl) {
        return zzjl.zzid() == zzjy.zzabd;
    }

    private static zzjk zzhw() {
        try {
            return (zzjk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzzy;
        }
    }
}
