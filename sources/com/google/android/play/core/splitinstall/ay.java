package com.google.android.play.core.splitinstall;

import android.os.Bundle;
import android.os.RemoteException;
import com.facebook.internal.NativeProtocol;
import com.google.android.play.core.internal.bq;
import com.google.android.play.core.tasks.i;
import java.util.List;

class ay<T> extends bq {
    final i<T> a;
    final /* synthetic */ az b;

    ay(az azVar, i<T> iVar) {
        this.b = azVar;
        this.a = iVar;
    }

    public final void a() throws RemoteException {
        this.b.a.a();
        az.b.c("onCompleteInstallForAppUpdate", new Object[0]);
    }

    public final void a(int i) throws RemoteException {
        this.b.a.a();
        az.b.c("onCompleteInstall(%d)", Integer.valueOf(i));
    }

    public void a(int i, Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onCancelInstall(%d)", Integer.valueOf(i));
    }

    public void a(Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onDeferredInstall", new Object[0]);
    }

    public void a(List<Bundle> list) throws RemoteException {
        this.b.a.a();
        az.b.c("onGetSessionStates", new Object[0]);
    }

    public final void b() throws RemoteException {
        this.b.a.a();
        az.b.c("onGetSplitsForAppUpdate", new Object[0]);
    }

    public void b(int i, Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onGetSession(%d)", Integer.valueOf(i));
    }

    public void b(Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onDeferredLanguageInstall", new Object[0]);
    }

    public void c(int i, Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onStartInstall(%d)", Integer.valueOf(i));
    }

    public void c(Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onDeferredLanguageUninstall", new Object[0]);
    }

    public void d(Bundle bundle) throws RemoteException {
        this.b.a.a();
        az.b.c("onDeferredUninstall", new Object[0]);
    }

    public final void e(Bundle bundle) throws RemoteException {
        this.b.a.a();
        int i = bundle.getInt(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        az.b.b("onError(%d)", Integer.valueOf(i));
        this.a.b((Exception) new SplitInstallException(i));
    }
}
