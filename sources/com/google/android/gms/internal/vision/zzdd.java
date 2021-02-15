package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdd<T> extends zzcy<T> {
    private final T zzma;

    zzdd(T t) {
        this.zzma = t;
    }

    public final boolean isPresent() {
        return true;
    }

    public final T get() {
        return this.zzma;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzdd) {
            return this.zzma.equals(((zzdd) obj).zzma);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzma.hashCode() + 1502476572;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzma);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
