package com.google.android.play.core.internal;

final class ae extends ab {
    final /* synthetic */ ak a;

    ae(ak akVar) {
        this.a = akVar;
    }

    public final void a() {
        if (this.a.l != null) {
            this.a.c.c("Unbind from service.", new Object[0]);
            this.a.b.unbindService(this.a.k);
            this.a.f = false;
            this.a.l = null;
            this.a.k = null;
        }
    }
}
