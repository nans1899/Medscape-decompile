package com.comscore.android.task;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskExecutor {
    private static final long e = 50000;
    private b a;
    private BlockingQueue<a> b;
    private boolean c;
    /* access modifiers changed from: private */
    public TaskExceptionHandler d;

    class a implements TaskExceptionHandler {
        a() {
        }

        public void exception(Exception exc, TaskExecutor taskExecutor, Runnable runnable) {
            exc.printStackTrace();
        }
    }

    class b implements Runnable {
        final /* synthetic */ Runnable a;

        b(Runnable runnable) {
            this.a = runnable;
        }

        public void run() {
            try {
                this.a.run();
            } catch (Exception e) {
                if (TaskExecutor.this.d != null) {
                    TaskExecutor.this.d.exception(e, TaskExecutor.this, this.a);
                }
            }
        }
    }

    public TaskExecutor() {
        this(new a());
    }

    public TaskExecutor(TaskExceptionHandler taskExceptionHandler) {
        this.c = true;
        this.d = taskExceptionHandler;
        this.b = new LinkedBlockingQueue();
        b bVar = new b(this, this.d);
        this.a = bVar;
        bVar.start();
    }

    /* access modifiers changed from: package-private */
    public a a() {
        for (a aVar : this.b) {
            if (aVar.c() <= System.currentTimeMillis()) {
                return aVar;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        this.b.remove(aVar);
    }

    /* access modifiers changed from: package-private */
    public long b() {
        long j = e;
        for (a d2 : this.b) {
            j = Math.min(j, d2.d());
        }
        return j;
    }

    public boolean containsTask(Runnable runnable) {
        for (a aVar : this.b) {
            if (aVar.f() == runnable) {
                return true;
            }
            if ((runnable instanceof a) && aVar == runnable) {
                return true;
            }
        }
        return false;
    }

    public boolean execute(Runnable runnable) {
        return execute(runnable, true);
    }

    public boolean execute(Runnable runnable, long j) {
        return execute(runnable, j, 0);
    }

    public boolean execute(Runnable runnable, long j, long j2) {
        return execute(runnable, j, j2, false);
    }

    public boolean execute(Runnable runnable, long j, long j2, boolean z) {
        if (!this.c) {
            return false;
        }
        for (a aVar : this.b) {
            if (aVar != null && aVar.f() == runnable) {
                return false;
            }
        }
        this.b.add(new a(runnable, j, j2, z));
        this.a.a();
        return true;
    }

    public boolean execute(Runnable runnable, boolean z) {
        if (z) {
            return execute(runnable, 0);
        }
        if (!this.c) {
            return false;
        }
        try {
            runnable.run();
            return true;
        } catch (Exception e2) {
            TaskExceptionHandler taskExceptionHandler = this.d;
            if (taskExceptionHandler == null) {
                return true;
            }
            taskExceptionHandler.exception(e2, this, runnable);
            return true;
        }
    }

    public boolean executeInMainThread(Runnable runnable) {
        if (!this.c) {
            return false;
        }
        return new Handler(Looper.getMainLooper()).post(new b(runnable));
    }

    public boolean isEnabled() {
        return this.c;
    }

    public void removeAllEnqueuedTasks() {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.b) {
            if (aVar.g()) {
                arrayList.add(aVar);
            }
        }
        this.b.removeAll(arrayList);
    }

    public boolean removeEnqueuedTask(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        for (a aVar : this.b) {
            if (aVar.f() == runnable) {
                return this.b.remove(aVar);
            }
        }
        return false;
    }

    public void setEnabled(boolean z) {
        this.c = z;
    }

    public int size() {
        return this.b.size();
    }

    public void waitForLastNonDelayedTaskToFinish() {
        a aVar;
        int size = this.b.size();
        a[] aVarArr = new a[size];
        this.b.toArray(aVarArr);
        int i = size - 1;
        while (true) {
            if (i >= 0) {
                if (aVarArr[i] != null && !aVarArr[i].h()) {
                    aVar = aVarArr[i];
                    break;
                }
                i--;
            } else {
                aVar = null;
                break;
            }
        }
        waitForTaskToFinish(aVar, 0);
    }

    public void waitForTaskToFinish(Runnable runnable, long j) {
        a aVar;
        if (!(runnable instanceof a)) {
            Iterator it = this.b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    aVar = null;
                    break;
                }
                aVar = (a) it.next();
                if (aVar.f() == runnable) {
                    break;
                }
            }
        } else {
            aVar = (a) runnable;
        }
        if (aVar != null) {
            long currentTimeMillis = System.currentTimeMillis();
            while (this.b.contains(aVar)) {
                if (j <= 0 || System.currentTimeMillis() < currentTimeMillis + j) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void waitForTasks() {
        while (this.b.size() != 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }
}
