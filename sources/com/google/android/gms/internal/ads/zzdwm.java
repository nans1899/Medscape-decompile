package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public abstract class zzdwm<E> {
    zzdwm() {
    }

    public abstract zzdwm<E> zzaa(E e);

    public zzdwm<E> zzg(Iterable<? extends E> iterable) {
        for (Object zzaa : iterable) {
            zzaa(zzaa);
        }
        return this;
    }

    public zzdwm<E> zza(Iterator<? extends E> it) {
        while (it.hasNext()) {
            zzaa(it.next());
        }
        return this;
    }
}
