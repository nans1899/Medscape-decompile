package mnetinternal;

import android.os.Build;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class aa {
    private static final int a = (cv.e() * 4);
    private static aa e = new aa();
    private static AtomicBoolean f = new AtomicBoolean(false);
    private ScheduledExecutorService b = Executors.newScheduledThreadPool(a);
    private ScheduledExecutorService c;
    private ConcurrentLinkedQueue<Runnable> d;

    private aa() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        if (Build.VERSION.SDK_INT >= 21) {
            scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
            ScheduledExecutorService scheduledExecutorService = this.b;
            if (scheduledExecutorService instanceof ScheduledThreadPoolExecutor) {
                ((ScheduledThreadPoolExecutor) scheduledExecutorService).setRemoveOnCancelPolicy(true);
            }
        }
        this.c = Executors.unconfigurableScheduledExecutorService(scheduledThreadPoolExecutor);
        this.d = new ConcurrentLinkedQueue<>();
    }

    private Future b(Runnable runnable) {
        if (f.get()) {
            return this.b.submit(runnable);
        }
        this.d.add(runnable);
        return null;
    }

    private Future b(Callable callable) {
        return this.b.submit(callable);
    }

    private void c() {
        Iterator<Runnable> it = this.d.iterator();
        while (it.hasNext()) {
            e.b(it.next());
        }
        this.d.clear();
    }

    private ScheduledFuture b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.b.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    private ScheduledFuture b(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.b.schedule(runnable, j, timeUnit);
    }

    public static ScheduledExecutorService a() {
        return e.c;
    }

    public static ScheduledFuture a(Runnable runnable, long j, TimeUnit timeUnit) {
        return e.b(runnable, j, timeUnit);
    }

    public static ScheduledFuture a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return e.b(runnable, j, j2, timeUnit);
    }

    public static Future a(Runnable runnable) {
        return e.b(runnable);
    }

    public static Future a(Callable callable) {
        return e.b(callable);
    }

    public static void b() {
        f.set(true);
        e.c();
    }
}
