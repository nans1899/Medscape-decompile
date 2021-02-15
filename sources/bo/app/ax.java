package bo.app;

import java.lang.Thread;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ax implements ThreadFactory {
    private final AtomicInteger a;
    private final String b;
    private Thread.UncaughtExceptionHandler c;

    public ax() {
        this.a = new AtomicInteger(1);
        this.b = ax.class.getSimpleName();
    }

    public ax(String str) {
        this.a = new AtomicInteger(1);
        this.b = str;
    }

    public void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.c = uncaughtExceptionHandler;
    }

    public Thread newThread(Runnable runnable) {
        if (this.c != null) {
            Thread thread = new Thread(runnable, this.b + " #" + this.a.getAndIncrement());
            thread.setUncaughtExceptionHandler(this.c);
            return thread;
        }
        throw new IllegalStateException("No UncaughtExceptionHandler. You must call setUncaughtExceptionHandler before creating a new thread");
    }
}
