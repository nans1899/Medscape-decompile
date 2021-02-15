package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

public class df implements dl {
    private static final String a = AppboyLogger.getAppboyLogTag(df.class);
    /* access modifiers changed from: private */
    public final dl b;
    private final ThreadPoolExecutor c;
    private boolean d = false;

    public df(dl dlVar, ThreadPoolExecutor threadPoolExecutor) {
        this.b = dlVar;
        this.c = threadPoolExecutor;
    }

    public void a(final bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not adding event: " + bzVar);
            return;
        }
        this.c.execute(new Runnable() {
            public void run() {
                df.this.b.a(bzVar);
            }
        });
    }

    public void b(final bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not deleting event: " + bzVar);
            return;
        }
        this.c.execute(new Runnable() {
            public void run() {
                df.this.b.b(bzVar);
            }
        });
    }

    public synchronized Collection<bz> a() {
        if (this.d) {
            AppboyLogger.w(a, "Storage provider is closed. Not getting all events.");
            return null;
        }
        try {
            return (Collection) this.c.submit(new Callable<Collection<bz>>() {
                /* renamed from: a */
                public Collection<bz> call() {
                    return df.this.b.a();
                }
            }).get();
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to asynchronously get all events.", e);
        }
    }

    public synchronized void b() {
        AppboyLogger.w(a, "Setting this provider and internal storage provider to closed. Cancelling all queued storage provider work.");
        this.d = true;
        this.b.b();
        this.c.shutdownNow();
    }
}
