package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

final class b<ResultT> implements g<ResultT> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public final OnCompleteListener<ResultT> c;

    public b(Executor executor, OnCompleteListener<ResultT> onCompleteListener) {
        this.a = executor;
        this.c = onCompleteListener;
    }

    public final void a(Task<ResultT> task) {
        synchronized (this.b) {
            if (this.c != null) {
                this.a.execute(new a(this, task));
            }
        }
    }
}
