package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.HashSet;
import java.util.concurrent.Executor;

public class bh {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(bh.class);
    /* access modifiers changed from: private */
    public final dl b;
    /* access modifiers changed from: private */
    public final dl c;
    private boolean d = false;

    public bh(dl dlVar, dl dlVar2) {
        this.c = dlVar;
        this.b = dlVar2;
    }

    public void a(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage manager is closed. Not adding event: " + bzVar);
            return;
        }
        this.c.a(bzVar);
    }

    public void b(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage manager is closed. Not deleting event: " + bzVar);
            return;
        }
        this.c.b(bzVar);
    }

    public void a(Executor executor, final t tVar) {
        if (this.d) {
            AppboyLogger.w(a, "Storage manager is closed. Not starting offline recovery.");
        } else {
            executor.execute(new Runnable() {
                public void run() {
                    AppboyLogger.d(bh.a, "Started offline AppboyEvent recovery task.");
                    bh.a(tVar, bh.this.c, bh.this.b);
                }
            });
        }
    }

    public void a() {
        this.d = true;
        this.c.b();
    }

    static void a(t tVar, dl dlVar, dl dlVar2) {
        HashSet hashSet = new HashSet();
        for (bz next : dlVar.a()) {
            String str = a;
            AppboyLogger.v(str, "Adding event to dispatch from active storage: " + next);
            hashSet.add(next.d());
            tVar.a(next);
        }
        if (dlVar2 != null) {
            for (bz next2 : dlVar2.a()) {
                dlVar2.b(next2);
                if (hashSet.contains(next2.d())) {
                    String str2 = a;
                    AppboyLogger.d(str2, "Event present in both storage providers. Not re-adding to current storage: " + next2);
                } else {
                    String str3 = a;
                    AppboyLogger.d(str3, "Found event in storage from migrated storage provider: " + next2);
                    dlVar.a(next2);
                }
            }
        }
    }
}
