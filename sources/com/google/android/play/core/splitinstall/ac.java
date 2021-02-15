package com.google.android.play.core.splitinstall;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.listener.b;

public final class ac extends b<SplitInstallSessionState> {
    private static ac c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private final c e;

    public ac(Context context, c cVar) {
        super(new aa("SplitInstallListenerRegistry"), new IntentFilter("com.google.android.play.core.splitinstall.receiver.SplitInstallUpdateIntentService"), context);
        this.e = cVar;
    }

    public static synchronized ac a(Context context) {
        ac acVar;
        synchronized (ac.class) {
            if (c == null) {
                c = new ac(context, w.a);
            }
            acVar = c;
        }
        return acVar;
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("session_state");
        if (bundleExtra != null) {
            SplitInstallSessionState a = SplitInstallSessionState.a(bundleExtra);
            this.a.a("ListenerRegistryBroadcastReceiver.onReceive: %s", a);
            d a2 = this.e.a();
            if (a.status() == 3 && a2 != null) {
                a2.a(a.c(), new aa(this, a, intent, context));
            } else {
                a(a);
            }
        }
    }
}
