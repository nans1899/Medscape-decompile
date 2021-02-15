package com.comscore.android.task;

import java.util.concurrent.atomic.AtomicBoolean;

class a implements Runnable {
    private AtomicBoolean a;
    private AtomicBoolean b;
    private Runnable c;
    private long d;
    private long e;
    private long f;
    private boolean g;
    private boolean h;
    private Exception i;

    a(Runnable runnable) {
        this(runnable, 0);
    }

    a(Runnable runnable, long j) {
        this(runnable, j, 0);
    }

    a(Runnable runnable, long j, long j2) {
        this(runnable, j, j2, true);
    }

    a(Runnable runnable, long j, long j2, boolean z) {
        this.c = runnable;
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        this.d = System.currentTimeMillis() + (i2 <= 0 ? 0 : j);
        this.g = i2 > 0;
        this.e = System.currentTimeMillis();
        this.f = j2;
        this.a = new AtomicBoolean();
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.b = atomicBoolean;
        atomicBoolean.set(false);
        this.a.set(false);
        this.i = null;
        this.h = z;
    }

    /* access modifiers changed from: package-private */
    public long a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public Exception b() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public long c() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public long d() {
        long currentTimeMillis = this.d - System.currentTimeMillis();
        if (currentTimeMillis > 0) {
            return currentTimeMillis;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public Runnable f() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.b.get();
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.f > 0;
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return this.a.get();
    }

    public void run() {
        this.a.set(true);
        try {
            this.c.run();
        } catch (Exception e2) {
            this.i = e2;
        }
        this.a.set(false);
        this.b.set(true);
    }
}
