package com.google.android.play.core.internal;

import android.os.IBinder;
import android.os.IInterface;

final class ah extends ab {
    final /* synthetic */ IBinder a;
    final /* synthetic */ aj b;

    ah(aj ajVar, IBinder iBinder) {
        this.b = ajVar;
        this.a = iBinder;
    }

    public final void a() {
        ak akVar = this.b.a;
        akVar.l = (IInterface) akVar.h.a(this.a);
        ak.f(this.b.a);
        this.b.a.f = false;
        for (Runnable run : this.b.a.e) {
            run.run();
        }
        this.b.a.e.clear();
    }
}
