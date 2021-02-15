package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public final class zzkw extends AbstractList<String> implements zzis, RandomAccess {
    /* access modifiers changed from: private */
    public final zzis zza;

    public zzkw(zzis zzis) {
        this.zza = zzis;
    }

    public final zzis zze() {
        return this;
    }

    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final void zza(zzgr zzgr) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzkv(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzky(this);
    }

    public final List<?> zzd() {
        return this.zza.zzd();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }
}
