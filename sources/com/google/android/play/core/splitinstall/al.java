package com.google.android.play.core.splitinstall;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.tasks.i;
import java.util.Collection;
import java.util.List;

final class al extends ab {
    final /* synthetic */ List a;
    final /* synthetic */ i b;
    final /* synthetic */ az c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    al(az azVar, i iVar, List list, i iVar2) {
        super(iVar);
        this.c = azVar;
        this.a = list;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.c.a.b().d(this.c.d, az.b((Collection) this.a), az.b(), new as(this.c, this.b));
        } catch (RemoteException e) {
            az.b.a((Throwable) e, "deferredLanguageInstall(%s)", this.a);
            this.b.b((Exception) new RuntimeException(e));
        }
    }
}
