package bo.app;

import com.appboy.support.AppboyLogger;

public class dj implements dn {
    private static final String a = AppboyLogger.getAppboyLogTag(dj.class);
    private final dn b;
    private final ad c;

    public dj(dn dnVar, ad adVar) {
        this.b = dnVar;
        this.c = adVar;
    }

    public void a(cc ccVar) {
        try {
            this.b.a(ccVar);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to upsert active session in the storage.", e);
            a(this.c, e);
        }
    }

    public cc a() {
        try {
            return this.b.a();
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to get the active session from the storage.", e);
            a(this.c, e);
            return null;
        }
    }

    public void b(cc ccVar) {
        try {
            this.b.b(ccVar);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to delete the sealed session from the storage.", e);
            a(this.c, e);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ad adVar, Throwable th) {
        try {
            adVar.a(new av("A database exception has occurred. Please view the stack trace for more details.", th), av.class);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to log throwable.", e);
        }
    }
}
