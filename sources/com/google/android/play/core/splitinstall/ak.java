package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.tasks.i;
import java.util.Collection;
import java.util.List;

final class ak extends ab {
    final /* synthetic */ List a;
    final /* synthetic */ i b;
    final /* synthetic */ az c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ak(az azVar, i iVar, List list, i iVar2) {
        super(iVar);
        this.c = azVar;
        this.a = list;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.c.a.b().c(this.c.d, az.a((Collection) this.a), az.b(), new ar(this.c, this.b));
        } catch (RemoteException e) {
            az.b.a((Throwable) e, "deferredInstall(%s)", this.a);
            this.b.b((Exception) new RuntimeException(e));
        }
    }
}
