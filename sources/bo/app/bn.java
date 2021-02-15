package bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.appboy.Appboy;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.TimeUnit;

public class bn {
    static final long a = TimeUnit.SECONDS.toMillis(10);
    /* access modifiers changed from: private */
    public static final String b = AppboyLogger.getAppboyLogTag(bn.class);
    private static final long c = TimeUnit.SECONDS.toMillis(10);
    /* access modifiers changed from: private */
    public final Object d = new Object();
    private final dn e;
    /* access modifiers changed from: private */
    public final ad f;
    private final Context g;
    private final AlarmManager h;
    private final int i;
    private final String j;
    private final dq k;
    private volatile cc l;
    private final Handler m;
    private final Runnable n;
    private final boolean o;

    public bn(final Context context, dn dnVar, ad adVar, AlarmManager alarmManager, dq dqVar, int i2, boolean z) {
        this.e = dnVar;
        this.f = adVar;
        this.g = context;
        this.h = alarmManager;
        this.i = i2;
        this.k = dqVar;
        this.m = ea.a();
        this.n = new Runnable() {
            public void run() {
                AppboyLogger.d(bn.b, "Requesting data flush on internal session close flush timer.");
                Appboy.getInstance(context).requestImmediateDataFlush();
            }
        };
        this.o = z;
        AnonymousClass2 r3 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                synchronized (bn.this.d) {
                    try {
                        bn.this.k();
                    } catch (Exception e) {
                        try {
                            bn.this.f.a(e, Throwable.class);
                        } catch (Exception e2) {
                            AppboyLogger.e(bn.b, "Failed to log throwable.", e2);
                        }
                    }
                }
            }
        };
        this.j = context.getPackageName() + ".intent.APPBOY_SESSION_SHOULD_SEAL";
        context.registerReceiver(r3, new IntentFilter(this.j));
    }

    public cc a() {
        cc ccVar;
        synchronized (this.d) {
            if (i()) {
                this.e.a(this.l);
            }
            g();
            l();
            this.f.a(aq.a, aq.class);
            ccVar = this.l;
        }
        return ccVar;
    }

    public cc b() {
        cc ccVar;
        synchronized (this.d) {
            i();
            this.l.a(Double.valueOf(du.b()));
            this.e.a(this.l);
            f();
            a(b(this.l, this.i, this.o));
            this.f.a(ar.a, ar.class);
            ccVar = this.l;
        }
        return ccVar;
    }

    public cd c() {
        synchronized (this.d) {
            k();
            if (this.l == null) {
                return null;
            }
            cd a2 = this.l.a();
            return a2;
        }
    }

    public boolean d() {
        boolean z;
        synchronized (this.d) {
            z = this.l != null && this.l.d();
        }
        return z;
    }

    public void e() {
        synchronized (this.d) {
            if (this.l != null) {
                this.l.e();
                this.e.a(this.l);
                this.f.a(new ap(this.l), ap.class);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.d
            monitor-enter(r0)
            r6.k()     // Catch:{ all -> 0x0058 }
            bo.app.cc r1 = r6.l     // Catch:{ all -> 0x0058 }
            r2 = 1
            if (r1 == 0) goto L_0x0027
            bo.app.cc r1 = r6.l     // Catch:{ all -> 0x0058 }
            boolean r1 = r1.d()     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x0014
            goto L_0x0027
        L_0x0014:
            bo.app.cc r1 = r6.l     // Catch:{ all -> 0x0058 }
            java.lang.Double r1 = r1.c()     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x0024
            bo.app.cc r1 = r6.l     // Catch:{ all -> 0x0058 }
            r3 = 0
            r1.a(r3)     // Catch:{ all -> 0x0058 }
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            return r2
        L_0x0024:
            r1 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            return r1
        L_0x0027:
            bo.app.cc r1 = r6.l     // Catch:{ all -> 0x0058 }
            bo.app.cc r3 = r6.j()     // Catch:{ all -> 0x0058 }
            r6.l = r3     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x0056
            boolean r3 = r1.d()     // Catch:{ all -> 0x0058 }
            if (r3 == 0) goto L_0x0056
            java.lang.String r3 = b     // Catch:{ all -> 0x0058 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0058 }
            r4.<init>()     // Catch:{ all -> 0x0058 }
            java.lang.String r5 = "Clearing completely dispatched sealed session "
            r4.append(r5)     // Catch:{ all -> 0x0058 }
            bo.app.cd r5 = r1.a()     // Catch:{ all -> 0x0058 }
            r4.append(r5)     // Catch:{ all -> 0x0058 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0058 }
            com.appboy.support.AppboyLogger.d(r3, r4)     // Catch:{ all -> 0x0058 }
            bo.app.dn r3 = r6.e     // Catch:{ all -> 0x0058 }
            r3.b(r1)     // Catch:{ all -> 0x0058 }
        L_0x0056:
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            return r2
        L_0x0058:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0058 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bn.i():boolean");
    }

    private cc j() {
        cc ccVar = new cc(cd.a(), du.b());
        this.k.a(true);
        this.f.a(ao.a, ao.class);
        String str = b;
        AppboyLogger.i(str, "New session created with ID: " + ccVar.a());
        return ccVar;
    }

    /* access modifiers changed from: private */
    public void k() {
        synchronized (this.d) {
            if (this.l == null) {
                this.l = this.e.a();
                if (this.l != null) {
                    String str = b;
                    AppboyLogger.d(str, "Restored session from offline storage: " + this.l.a().toString());
                }
            }
            if (this.l != null && this.l.c() != null && !this.l.d() && a(this.l, this.i, this.o)) {
                String str2 = b;
                AppboyLogger.i(str2, "Session [" + this.l.a() + "] being sealed because its end time is over the grace period.");
                e();
                this.e.b(this.l);
                this.l = null;
            }
        }
    }

    private void a(long j2) {
        String str = b;
        AppboyLogger.d(str, "Creating a session seal alarm with a delay of " + j2 + " ms");
        Intent intent = new Intent(this.j);
        intent.putExtra("session_id", this.l.toString());
        this.h.set(1, du.c() + j2, PendingIntent.getBroadcast(this.g, 0, intent, 1073741824));
    }

    private void l() {
        Intent intent = new Intent(this.j);
        intent.putExtra("session_id", this.l.toString());
        this.h.cancel(PendingIntent.getBroadcast(this.g, 0, intent, 1073741824));
    }

    static boolean a(cc ccVar, int i2, boolean z) {
        long c2 = du.c();
        long millis = TimeUnit.SECONDS.toMillis((long) i2);
        long millis2 = TimeUnit.SECONDS.toMillis(ccVar.c().longValue());
        long millis3 = TimeUnit.SECONDS.toMillis((long) ccVar.b());
        if (z) {
            if (millis3 + millis + a <= c2) {
                return true;
            }
            return false;
        } else if (millis2 + millis <= c2) {
            return true;
        } else {
            return false;
        }
    }

    static long b(cc ccVar, int i2, boolean z) {
        long millis = TimeUnit.SECONDS.toMillis((long) i2);
        if (!z) {
            return millis;
        }
        long millis2 = TimeUnit.SECONDS.toMillis((long) ccVar.b());
        return Math.max(a, (millis2 + millis) - du.c());
    }

    /* access modifiers changed from: protected */
    public void f() {
        g();
        this.m.postDelayed(this.n, c);
    }

    /* access modifiers changed from: protected */
    public void g() {
        this.m.removeCallbacks(this.n);
    }
}
