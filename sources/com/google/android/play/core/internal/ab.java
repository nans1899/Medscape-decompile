package com.google.android.play.core.internal;

import com.google.android.play.core.tasks.i;

public abstract class ab implements Runnable {
    private final i<?> a;

    ab() {
        this.a = null;
    }

    public ab(i<?> iVar) {
        this.a = iVar;
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: package-private */
    public final i<?> b() {
        return this.a;
    }

    public final void run() {
        try {
            a();
        } catch (Exception e) {
            i<?> iVar = this.a;
            if (iVar != null) {
                iVar.b(e);
            }
        }
    }
}
