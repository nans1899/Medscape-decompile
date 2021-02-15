package com.google.android.play.core.splitinstall;

import java.util.concurrent.atomic.AtomicReference;

public enum w implements c {
    ;
    
    private static final AtomicReference<d> b = null;

    static {
        a = new w("INSTANCE");
        b = new AtomicReference<>((Object) null);
    }

    private w(String str) {
    }

    public final d a() {
        return b.get();
    }

    public final void a(d dVar) {
        b.compareAndSet((Object) null, dVar);
    }
}
