package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.tasks.i;

final class f extends ab {
    final /* synthetic */ String a;
    final /* synthetic */ i b;
    final /* synthetic */ k c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    f(k kVar, i iVar, String str, i iVar2) {
        super(iVar);
        this.c = kVar;
        this.a = str;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            this.c.a.b().a(this.c.d, k.a(this.c, this.a), new j(this.c, this.b, this.a));
        } catch (RemoteException e) {
            k.b.a((Throwable) e, "requestUpdateInfo(%s)", this.a);
            this.b.b((Exception) new RuntimeException(e));
        }
    }
}
