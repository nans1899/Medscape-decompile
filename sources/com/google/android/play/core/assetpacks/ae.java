package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.internal.s;
import com.google.android.play.core.internal.u;
import com.google.android.play.core.tasks.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class ae extends ab {
    final /* synthetic */ List a;
    final /* synthetic */ i b;
    final /* synthetic */ az c;
    final /* synthetic */ ar d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ae(ar arVar, i iVar, List list, i iVar2, az azVar) {
        super(iVar);
        this.d = arVar;
        this.a = list;
        this.b = iVar2;
        this.c = azVar;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        ArrayList a2 = ar.a((Collection) this.a);
        try {
            String a3 = this.d.c;
            Bundle c2 = ar.c();
            ar arVar = this.d;
            ((s) this.d.e.b()).c(a3, (List<Bundle>) a2, c2, (u) new ap(arVar, this.b, arVar.d, this.c));
        } catch (RemoteException e) {
            ar.a.a((Throwable) e, "getPackStates(%s)", this.a);
            this.b.b((Exception) new RuntimeException(e));
        }
    }
}
