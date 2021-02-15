package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.internal.br;
import com.google.android.play.core.tasks.i;

final class ap extends ab {
    final /* synthetic */ int a;
    final /* synthetic */ i b;
    final /* synthetic */ az c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ap(az azVar, i iVar, int i, i iVar2) {
        super(iVar);
        this.c = azVar;
        this.a = i;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.c.a.b().a(this.c.d, this.a, az.b(), (br) new aq(this.c, this.b));
        } catch (RemoteException e) {
            az.b.a((Throwable) e, "cancelInstall(%d)", Integer.valueOf(this.a));
            this.b.b((Exception) new RuntimeException(e));
        }
    }
}
