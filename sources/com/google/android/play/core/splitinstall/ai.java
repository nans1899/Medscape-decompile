package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.ab;
import com.google.android.play.core.internal.br;
import com.google.android.play.core.tasks.i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class ai extends ab {
    final /* synthetic */ Collection a;
    final /* synthetic */ Collection b;
    final /* synthetic */ i c;
    final /* synthetic */ az d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ai(az azVar, i iVar, Collection collection, Collection collection2, i iVar2) {
        super(iVar);
        this.d = azVar;
        this.a = collection;
        this.b = collection2;
        this.c = iVar2;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        ArrayList a2 = az.a(this.a);
        a2.addAll(az.b(this.b));
        try {
            this.d.a.b().a(this.d.d, (List<Bundle>) a2, az.b(), (br) new ax(this.d, this.c));
        } catch (RemoteException e) {
            az.b.a((Throwable) e, "startInstall(%s,%s)", this.a, this.b);
            this.c.b((Exception) new RuntimeException(e));
        }
    }
}
