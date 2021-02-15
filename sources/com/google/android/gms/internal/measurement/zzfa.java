package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfa<E> extends zzej<E> {
    private final zzfb<E> zza;

    zzfa(zzfb<E> zzfb, int i) {
        super(zzfb.size(), i);
        this.zza = zzfb;
    }

    /* access modifiers changed from: protected */
    public final E zza(int i) {
        return this.zza.get(i);
    }
}
