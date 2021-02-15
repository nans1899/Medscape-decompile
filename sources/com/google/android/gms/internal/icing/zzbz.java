package com.google.android.gms.internal.icing;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzbz<T> extends zzbx<T> {
    private final T zzdv;

    zzbz(T t) {
        this.zzdv = t;
    }

    public final boolean isPresent() {
        return true;
    }

    public final T get() {
        return this.zzdv;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzbz) {
            return this.zzdv.equals(((zzbz) obj).zzdv);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzdv.hashCode() + 1502476572;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzdv);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
