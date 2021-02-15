package bo.app;

import com.appboy.Appboy;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public final class r implements t {
    private static final String c = AppboyLogger.getAppboyLogTag(r.class);
    final ConcurrentHashMap<String, bz> a;
    final ConcurrentHashMap<String, bz> b;
    private final br d;
    private final dt e;
    private final LinkedBlockingQueue<cv> f = new LinkedBlockingQueue<>(1000);
    private final AppboyConfigurationProvider g;

    public r(dt dtVar, br brVar, AppboyConfigurationProvider appboyConfigurationProvider) {
        this.e = dtVar;
        this.d = brVar;
        this.g = appboyConfigurationProvider;
        this.a = new ConcurrentHashMap<>();
        this.b = new ConcurrentHashMap<>();
    }

    public synchronized void b(bz bzVar) {
        if (bzVar == null) {
            AppboyLogger.w(c, "Tried to add null AppboyEvent to pending dispatch.");
        } else {
            this.b.putIfAbsent(bzVar.d(), bzVar);
        }
    }

    public synchronized void a(cd cdVar) {
        if (!this.b.isEmpty()) {
            AppboyLogger.d(c, "Flushing pending events to dispatcher map");
            for (bz a2 : this.b.values()) {
                a2.a(cdVar);
            }
            this.a.putAll(this.b);
            this.b.clear();
        }
    }

    public void a(bz bzVar) {
        if (bzVar == null) {
            AppboyLogger.w(c, "Tried to add null AppboyEvent to dispatch.");
        } else {
            this.a.putIfAbsent(bzVar.d(), bzVar);
        }
    }

    public void a(cv cvVar) {
        if (cvVar == null) {
            throw null;
        } else if (d()) {
            AppboyLogger.i(c, "Network requests are offline, not adding request to queue.");
        } else {
            String str = c;
            AppboyLogger.i(str, "Adding request to dispatcher with parameters: \n" + ed.a(cvVar.g()), false);
            this.f.add(cvVar);
        }
    }

    public boolean a() {
        return !this.f.isEmpty();
    }

    public cv b() {
        return b(this.f.take());
    }

    public cv c() {
        cv poll = this.f.poll();
        if (poll != null) {
            b(poll);
        }
        return poll;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0024, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized bo.app.cv b(bo.app.cv r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0006
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x0006:
            r1.c(r2)     // Catch:{ all -> 0x0025 }
            boolean r0 = r2 instanceof bo.app.db     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r1)
            return r2
        L_0x000f:
            boolean r0 = r2 instanceof bo.app.ct     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0023
            boolean r0 = r2 instanceof bo.app.cu     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0018
            goto L_0x0023
        L_0x0018:
            boolean r0 = r2 instanceof bo.app.cq     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x001e
            monitor-exit(r1)
            return r2
        L_0x001e:
            r1.d(r2)     // Catch:{ all -> 0x0025 }
            monitor-exit(r1)
            return r2
        L_0x0023:
            monitor-exit(r1)
            return r2
        L_0x0025:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.r.b(bo.app.cv):bo.app.cv");
    }

    private void c(cv cvVar) {
        if (this.d.c() != null) {
            cvVar.a(this.d.c());
        }
        if (this.g.getAppboyApiKey() != null) {
            cvVar.b(this.g.getAppboyApiKey().toString());
        }
        cvVar.c("3.0.1");
        cvVar.a(du.a());
    }

    private void d(cv cvVar) {
        cvVar.d(this.d.e());
        cvVar.a(this.g.getSdkFlavor());
        cvVar.a(this.d.b());
        cvVar.a((ck) this.e.b());
        cvVar.a(e());
    }

    private synchronized bx e() {
        ArrayList arrayList;
        Collection<bz> values = this.a.values();
        arrayList = new ArrayList();
        for (bz next : values) {
            arrayList.add(next);
            values.remove(next);
            String str = c;
            AppboyLogger.d(str, "Event dispatched: " + next.forJsonPut() + " with uid: " + next.d());
        }
        return new bx(new HashSet(arrayList));
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return Appboy.getOutboundNetworkRequestsOffline();
    }
}
