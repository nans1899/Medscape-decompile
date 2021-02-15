package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfk<T> implements zzfu<T> {
    private final zzfh zzmn;
    private final zzgm<?, ?> zzmo;
    private final boolean zzmp;
    private final zzdn<?> zzmq;

    private zzfk(zzgm<?, ?> zzgm, zzdn<?> zzdn, zzfh zzfh) {
        this.zzmo = zzgm;
        this.zzmp = zzdn.zze(zzfh);
        this.zzmq = zzdn;
        this.zzmn = zzfh;
    }

    static <T> zzfk<T> zza(zzgm<?, ?> zzgm, zzdn<?> zzdn, zzfh zzfh) {
        return new zzfk<>(zzgm, zzdn, zzfh);
    }

    public final boolean equals(T t, T t2) {
        if (!this.zzmo.zzp(t).equals(this.zzmo.zzp(t2))) {
            return false;
        }
        if (this.zzmp) {
            return this.zzmq.zzd(t).equals(this.zzmq.zzd(t2));
        }
        return true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzmo.zzp(t).hashCode();
        return this.zzmp ? (hashCode * 53) + this.zzmq.zzd(t).hashCode() : hashCode;
    }

    public final void zzc(T t, T t2) {
        zzfw.zza(this.zzmo, t, t2);
        if (this.zzmp) {
            zzfw.zza(this.zzmq, t, t2);
        }
    }

    public final void zza(T t, zzhg zzhg) throws IOException {
        Iterator<Map.Entry<?, Object>> it = this.zzmq.zzd(t).iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzdu zzdu = (zzdu) next.getKey();
            if (zzdu.zzbh() != zzhh.MESSAGE || zzdu.zzbi() || zzdu.zzbj()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzek) {
                zzhg.zza(zzdu.zzbf(), (Object) ((zzek) next).zzcc().zzad());
            } else {
                zzhg.zza(zzdu.zzbf(), next.getValue());
            }
        }
        zzgm<?, ?> zzgm = this.zzmo;
        zzgm.zzc(zzgm.zzp(t), zzhg);
    }

    public final void zzf(T t) {
        this.zzmo.zzf(t);
        this.zzmq.zzf(t);
    }

    public final boolean zzm(T t) {
        return this.zzmq.zzd(t).isInitialized();
    }

    public final int zzn(T t) {
        zzgm<?, ?> zzgm = this.zzmo;
        int zzq = zzgm.zzq(zzgm.zzp(t)) + 0;
        return this.zzmp ? zzq + this.zzmq.zzd(t).zzbe() : zzq;
    }
}
