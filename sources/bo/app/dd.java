package bo.app;

import com.appboy.support.AppboyLogger;

public abstract class dd<T> {
    private static final String a = AppboyLogger.getAppboyLogTag(dd.class);
    private final Object b = new Object();
    private boolean c = false;

    /* access modifiers changed from: package-private */
    public abstract T a();

    /* access modifiers changed from: package-private */
    public abstract void a(T t, boolean z);

    public T b() {
        synchronized (this.b) {
            if (this.c) {
                AppboyLogger.d(a, "Received call to export dirty object, but the cache was already locked.", false);
                return null;
            }
            this.c = true;
            T a2 = a();
            return a2;
        }
    }

    public boolean b(T t, boolean z) {
        synchronized (this.b) {
            if (!this.c) {
                String str = a;
                AppboyLogger.w(str, "Tried to confirm outboundObject [" + t + "] with success [" + z + "], but the cache wasn't locked, so not doing anything.");
                return false;
            }
            a(t, z);
            this.c = false;
            synchronized (this) {
                AppboyLogger.i(a, "Notifying confirmAndUnlock listeners", false);
                notifyAll();
            }
            return true;
        }
    }

    public boolean c() {
        boolean z;
        synchronized (this.b) {
            z = this.c;
        }
        return z;
    }
}
