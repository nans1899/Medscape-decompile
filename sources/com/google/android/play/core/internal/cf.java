package com.google.android.play.core.internal;

public final class cf<T> implements ci<T> {
    private ci<T> a;

    public static <T> void a(ci<T> ciVar, ci<T> ciVar2) {
        bl.a(ciVar2);
        cf cfVar = (cf) ciVar;
        if (cfVar.a == null) {
            cfVar.a = ciVar2;
            return;
        }
        throw new IllegalStateException();
    }

    public final T a() {
        ci<T> ciVar = this.a;
        if (ciVar != null) {
            return ciVar.a();
        }
        throw new IllegalStateException();
    }
}
