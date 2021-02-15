package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Predicate;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzakm implements Predicate {
    private final zzahq zzdha;

    zzakm(zzahq zzahq) {
        this.zzdha = zzahq;
    }

    public final boolean apply(Object obj) {
        zzahq zzahq = (zzahq) obj;
        return (zzahq instanceof zzakp) && zzakp.zza((zzakp) zzahq).equals(this.zzdha);
    }
}
