package bo.app;

import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.ThreadFactory;

public class n implements t {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(n.class);
    private final AppboyConfigurationProvider b;
    private final cx c;
    /* access modifiers changed from: private */
    public final r d;
    private final Object e = new Object();
    private volatile boolean f = false;
    private volatile Thread g;
    /* access modifiers changed from: private */
    public volatile boolean h = true;
    private cy i;
    private boolean j = false;

    public n(AppboyConfigurationProvider appboyConfigurationProvider, ad adVar, cx cxVar, r rVar, ThreadFactory threadFactory, boolean z) {
        this.b = appboyConfigurationProvider;
        this.c = cxVar;
        this.d = rVar;
        this.g = threadFactory.newThread(new a());
        this.i = new cy(adVar);
        this.j = z;
    }

    private cr c() {
        return new cr(this.b.getBaseUrlForRequests());
    }

    public void a(bz bzVar) {
        this.d.a(bzVar);
    }

    public void a(cv cvVar) {
        this.d.a(cvVar);
    }

    public void b(bz bzVar) {
        this.d.b(bzVar);
    }

    public void a(cd cdVar) {
        this.d.a(cdVar);
    }

    public void a(ac acVar) {
        synchronized (this.e) {
            this.h = false;
            this.g.interrupt();
            this.g = null;
        }
        if (!this.d.a()) {
            this.d.a((cv) c());
        }
        cv c2 = this.d.c();
        if (c2 != null) {
            c(c2);
        }
        acVar.a();
    }

    public void a() {
        synchronized (this.e) {
            if (this.f) {
                AppboyLogger.d(a, "Automatic request execution start was previously requested, continuing without action.");
                return;
            }
            if (this.g != null) {
                this.g.start();
            }
            this.f = true;
        }
    }

    class a implements Runnable {
        private a() {
        }

        public void run() {
            while (n.this.h) {
                try {
                    n.this.b(n.this.d.b());
                } catch (InterruptedException e) {
                    String b = n.a;
                    AppboyLogger.e(b, "Automatic thread interrupted! [" + e.getMessage() + "]");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(cv cvVar) {
        if (cvVar.h() || this.j) {
            this.i.a(cvVar);
        } else {
            this.c.a(cvVar);
        }
    }

    private void c(cv cvVar) {
        if (cvVar.h() || this.j) {
            this.i.b(cvVar);
        } else {
            this.c.b(cvVar);
        }
    }
}
