package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzeu implements zzfx {
    private static final zzfe zzme = new zzex();
    private final zzfe zzmd;

    public zzeu() {
        this(new zzew(zzdy.zzbs(), zzch()));
    }

    private zzeu(zzfe zzfe) {
        this.zzmd = (zzfe) zzeb.zza(zzfe, "messageInfoFactory");
    }

    public final <T> zzfu<T> zzd(Class<T> cls) {
        zzfw.zzf((Class<?>) cls);
        zzff zzc = this.zzmd.zzc(cls);
        if (zzc.zzcp()) {
            if (zzdx.class.isAssignableFrom(cls)) {
                return zzfk.zza(zzfw.zzda(), zzdp.zzbb(), zzc.zzcq());
            }
            return zzfk.zza(zzfw.zzcy(), zzdp.zzbc(), zzc.zzcq());
        } else if (zzdx.class.isAssignableFrom(cls)) {
            if (zza(zzc)) {
                return zzfl.zza(cls, zzc, zzfo.zzcs(), zzer.zzcg(), zzfw.zzda(), zzdp.zzbb(), zzfc.zzcm());
            }
            return zzfl.zza(cls, zzc, zzfo.zzcs(), zzer.zzcg(), zzfw.zzda(), (zzdn<?>) null, zzfc.zzcm());
        } else if (zza(zzc)) {
            return zzfl.zza(cls, zzc, zzfo.zzcr(), zzer.zzcf(), zzfw.zzcy(), zzdp.zzbc(), zzfc.zzcl());
        } else {
            return zzfl.zza(cls, zzc, zzfo.zzcr(), zzer.zzcf(), zzfw.zzcz(), (zzdn<?>) null, zzfc.zzcl());
        }
    }

    private static boolean zza(zzff zzff) {
        return zzff.zzco() == zzdx.zze.zzku;
    }

    private static zzfe zzch() {
        try {
            return (zzfe) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzme;
        }
    }
}
