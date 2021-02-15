package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class aj implements ServiceConnection {
    final /* synthetic */ ak a;

    /* synthetic */ aj(ak akVar) {
        this.a = akVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.c.c("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.a.b((ab) new ah(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c.c("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.a.b((ab) new ai(this));
    }
}
