package com.comscore.android.task;

class b extends Thread {
    private boolean a = false;
    private Object b;
    private TaskExecutor c;
    private TaskExceptionHandler d;

    b(TaskExecutor taskExecutor, TaskExceptionHandler taskExceptionHandler) {
        this.d = taskExceptionHandler;
        this.c = taskExecutor;
        this.b = new Object();
    }

    private void a(long j) {
        synchronized (this.b) {
            try {
                this.b.wait(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.b) {
            this.b.notify();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.a = true;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        long b2 = this.c.b();
        if (b2 > 0) {
            a(b2);
        }
    }

    public void run() {
        TaskExceptionHandler taskExceptionHandler;
        while (!c()) {
            a a2 = this.c.a();
            if (a2 != null) {
                a2.run();
                if (!(a2.b() == null || (taskExceptionHandler = this.d) == null)) {
                    taskExceptionHandler.exception(a2.b(), this.c, a2.f());
                }
                this.c.a(a2);
                if (a2.j()) {
                    this.c.execute(a2.f(), a2.e(), a2.e());
                }
            } else {
                d();
            }
        }
    }
}
