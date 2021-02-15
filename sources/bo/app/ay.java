package bo.app;

import com.appboy.support.AppboyLogger;
import java.lang.Thread;

public class ay implements Thread.UncaughtExceptionHandler {
    private static final String a = AppboyLogger.getAppboyLogTag(ay.class);
    private ad b;

    public ay(ad adVar) {
        this.b = adVar;
    }

    public void a(ad adVar) {
        this.b = adVar;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            if (this.b != null) {
                AppboyLogger.w(a, "Uncaught exception from thread. Publishing as Throwable event.", th);
                this.b.a(th, Throwable.class);
            }
        } catch (Exception e) {
            AppboyLogger.w(a, "Failed to log throwable.", e);
        }
    }
}
