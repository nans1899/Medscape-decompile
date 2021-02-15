package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.tasks.i;

final class ao extends ab {
    final /* synthetic */ i a;
    final /* synthetic */ az b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ao(az azVar, i iVar, i iVar2) {
        super(iVar);
        this.b = azVar;
        this.a = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.b.a.b().a(this.b.d, new aw(this.b, this.a));
        } catch (RemoteException e) {
            az.b.a((Throwable) e, "getSessionStates", new Object[0]);
            this.a.b((Exception) new RuntimeException(e));
        }
    }
}
