package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcix<T> implements zzahq<Object> {
    private final WeakReference<T> zzgeu;
    private final String zzgev;
    private final zzahq<T> zzgew;
    private final /* synthetic */ zzcil zzgex;

    private zzcix(zzcil zzcil, WeakReference<T> weakReference, String str, zzahq<T> zzahq) {
        this.zzgex = zzcil;
        this.zzgeu = weakReference;
        this.zzgev = str;
        this.zzgew = zzahq;
    }

    public final void zza(Object obj, Map<String, String> map) {
        Object obj2 = this.zzgeu.get();
        if (obj2 == null) {
            this.zzgex.zzb(this.zzgev, this);
        } else {
            this.zzgew.zza(obj2, map);
        }
    }

    /* synthetic */ zzcix(zzcil zzcil, WeakReference weakReference, String str, zzahq zzahq, zzciq zzciq) {
        this(zzcil, weakReference, str, zzahq);
    }
}
