package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

public final class y {
    private static final AtomicReference<x> a = new AtomicReference<>((Object) null);

    static x a() {
        return a.get();
    }

    public static void a(x xVar) {
        a.compareAndSet((Object) null, xVar);
    }
}
