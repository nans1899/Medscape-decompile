package com.google.android.play.core.listener;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.splitcompat.d;
import com.google.android.play.core.splitinstall.v;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class b<StateT> {
    protected final aa a;
    protected final Set<StateUpdatedListener<StateT>> b = new HashSet();
    private final IntentFilter c;
    private final Context d;
    private a e = null;
    private volatile boolean f = false;

    protected b(aa aaVar, IntentFilter intentFilter, Context context) {
        this.a = aaVar;
        this.c = intentFilter;
        this.d = v.a(context);
    }

    private final void c() {
        a aVar;
        if ((this.f || !this.b.isEmpty()) && this.e == null) {
            a aVar2 = new a(this);
            this.e = aVar2;
            this.d.registerReceiver(aVar2, this.c);
        }
        if (!this.f && this.b.isEmpty() && (aVar = this.e) != null) {
            this.d.unregisterReceiver(aVar);
            this.e = null;
        }
    }

    public final synchronized void a() {
        this.a.c("clearListeners", new Object[0]);
        this.b.clear();
        c();
    }

    /* access modifiers changed from: protected */
    public abstract void a(Context context, Intent intent);

    public final synchronized void a(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.c("registerListener", new Object[0]);
        d.a(stateUpdatedListener, (Object) "Registered Play Core listener should not be null.");
        this.b.add(stateUpdatedListener);
        c();
    }

    public final synchronized void a(StateT statet) {
        Iterator it = new HashSet(this.b).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(statet);
        }
    }

    public final synchronized void a(boolean z) {
        this.f = z;
        c();
    }

    public final synchronized void b(StateUpdatedListener<StateT> stateUpdatedListener) {
        this.a.c("unregisterListener", new Object[0]);
        d.a(stateUpdatedListener, (Object) "Unregistered Play Core listener should not be null.");
        this.b.remove(stateUpdatedListener);
        c();
    }

    public final synchronized boolean b() {
        return this.e != null;
    }
}
