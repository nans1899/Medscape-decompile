package com.google.android.play.core.tasks;

import java.util.concurrent.Executor;

final class f<ResultT> implements g<ResultT> {
    private final Executor a;
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    public final OnSuccessListener<? super ResultT> c;

    public f(Executor executor, OnSuccessListener<? super ResultT> onSuccessListener) {
        this.a = executor;
        this.c = onSuccessListener;
    }

    public final void a(Task<ResultT> task) {
        if (task.isSuccessful()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.a.execute(new e(this, task));
                }
            }
        }
    }
}
