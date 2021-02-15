package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.internal.s;
import com.google.android.play.core.tasks.i;

final class ad extends ab {
    final /* synthetic */ i a;
    final /* synthetic */ ar b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ad(ar arVar, i iVar, i iVar2) {
        super(iVar);
        this.b = arVar;
        this.a = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            ((s) this.b.e.b()).a(this.b.c, ar.c(), new am(this.b, this.a));
        } catch (RemoteException e) {
            ar.a.a((Throwable) e, "syncPacks", new Object[0]);
            this.a.b((Exception) new RuntimeException(e));
        }
    }
}
