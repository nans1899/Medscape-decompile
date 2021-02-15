package com.google.android.play.core.assetpacks;

import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.internal.s;
import com.google.android.play.core.tasks.i;

final class aa extends ab {
    final /* synthetic */ String a;
    final /* synthetic */ i b;
    final /* synthetic */ ar c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    aa(ar arVar, i iVar, String str, i iVar2) {
        super(iVar);
        this.c = arVar;
        this.a = str;
        this.b = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        try {
            ((s) this.c.e.b()).e(this.c.c, ar.c(0, this.a), ar.c(), new ak(this.c, this.b, (short[]) null));
        } catch (RemoteException e) {
            ar.a.a((Throwable) e, "removePack(%s)", this.a);
        }
    }
}
