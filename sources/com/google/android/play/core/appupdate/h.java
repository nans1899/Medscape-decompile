package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.o;
import com.google.android.play.core.tasks.i;

class h<T> extends o {
    final aa a;
    final i<T> b;
    final /* synthetic */ k c;

    h(k kVar, aa aaVar, i<T> iVar) {
        this.c = kVar;
        this.a = aaVar;
        this.b = iVar;
    }

    public void a(Bundle bundle) throws RemoteException {
        this.c.a.a();
        this.a.c("onRequestInfo", new Object[0]);
    }

    public void b(Bundle bundle) throws RemoteException {
        this.c.a.a();
        this.a.c("onCompleteUpdate", new Object[0]);
    }
}
