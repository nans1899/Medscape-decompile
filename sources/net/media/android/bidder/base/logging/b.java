package net.media.android.bidder.base.logging;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;
import mnetinternal.aa;
import mnetinternal.ac;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.models.internal.Logger;

public final class b {
    private static b a;
    /* access modifiers changed from: private */
    public a b;
    /* access modifiers changed from: private */
    public List<Logger> c = new ArrayList();
    /* access modifiers changed from: private */
    public boolean d;

    private b() {
    }

    private void b(final Application application) {
        aa.a((Runnable) new ac() {
            public void a() {
                a unused = b.this.b = new c(application);
                boolean unused2 = b.this.d = true;
                for (Logger a2 : b.this.c) {
                    b.this.b.a(a2);
                }
                b.this.c.clear();
            }
        });
    }

    public static void a(Application application) {
        if (a == null) {
            a = new b();
        }
        a.b(application);
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(Logger logger) {
        if (logger.isErrorType() && !AdUnitConfig.getInstance().getPublisherConfig().shouldPushErrors()) {
            return;
        }
        if (!this.d) {
            this.c.add(logger);
        } else {
            this.b.a(logger);
        }
    }
}
