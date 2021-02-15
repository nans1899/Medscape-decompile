package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.tasks.i;

final class g extends ab {
    final /* synthetic */ i a;
    final /* synthetic */ String b;
    final /* synthetic */ k c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    g(k kVar, i iVar, i iVar2, String str) {
        super(iVar);
        this.c = kVar;
        this.a = iVar2;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.c.a.b().b(this.c.d, k.d(), new i(this.c, this.a));
        } catch (RemoteException e) {
            k.b.a((Throwable) e, "completeUpdate(%s)", this.b);
            this.a.b((Exception) new RuntimeException(e));
        }
    }
}
